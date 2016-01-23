package com.bunk3r.destinywrapper.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bunk3r.destinywrapper.enums.Platform;
import com.bunk3r.destinywrapper.utils.CookiesUtils;

public class DestinyLogInWebView extends WebView {
    protected static final String TAG = DestinyLogInWebView.class.getSimpleName();
    public static final String REDIRECT_FINISH = "https://www.bungie.net/";
    private DestinyLogInListener mListener;

    public DestinyLogInWebView(Context context) {
        super(context);
        init();
    }

    public DestinyLogInWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DestinyLogInWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public DestinyLogInWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public DestinyLogInWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init() {
        requestFocus();
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String loadingUrl) {
                Log.e(TAG, loadingUrl);
                if (REDIRECT_FINISH.equals(loadingUrl)) {
                    String serverToken = CookiesUtils.getCookies(loadingUrl);
                    if (mListener != null) {
                        if (serverToken != null) {
                            mListener.onUserLoggedIn(serverToken, CookiesUtils.getCrossReferenceToken(serverToken));
                        } else {
                            mListener.onLoginFailed();
                        }
                    }

                    clearUserCookies();
                }

                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (mListener != null) {
                    mListener.onLoginFailed();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            if (!hasFocus()) {
                requestFocus();
            }
        }
        return super.onTouchEvent(event);
    }

    public void setLogInListener(DestinyLogInListener listener) {
        mListener = listener;
    }

    public void loadLoginUrl(@Platform.VALUES int platform) {
        String loginUrl = Platform.loginUrl(getContext(), platform);
        super.loadUrl(loginUrl);
    }

    public void clearUserCookies() {
        CookieManager.getInstance().removeAllCookie();
    }

    @TargetApi(21)
    public void clearUserCookies(ValueCallback<Boolean> callback) {
        CookieManager.getInstance().removeAllCookies(callback);
    }

    public interface DestinyLogInListener {

        void onUserLoggedIn(String cookies, String crossRefToken);

        void onLoginFailed();

    }
}