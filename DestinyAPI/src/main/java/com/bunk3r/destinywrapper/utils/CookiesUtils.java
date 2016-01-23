package com.bunk3r.destinywrapper.utils;

import android.support.annotation.Nullable;
import android.webkit.CookieManager;

import com.bunk3r.destinywrapper.Constants;

public final class CookiesUtils {

    private CookiesUtils() {
        throw new UnsupportedOperationException("Don't call this constructor");
    }

    public static String getCookies(String siteUrl) {
        CookieManager cookieManager = CookieManager.getInstance();
        return cookieManager.getCookie(siteUrl);
    }

    @Nullable
    public static String getCrossReferenceToken(String cookies) {
        if (cookies == null) {
            return null;
        }

        String[] rawCookies = cookies.split("[; ]+");

        if (rawCookies.length > 1) {
            for (String rawCookie : rawCookies) {
                int splitPos = rawCookie.indexOf('=');
                if (splitPos != -1) {
                    String name = rawCookie.substring(0, splitPos);
                    String value = rawCookie.substring(splitPos + 1, rawCookie.length());

                    if (Constants.DESTINY_CSRF_COOKIE_NAME.equals(name)) {
                        return value;
                    }
                }
            }
        }

        return null;
    }

}