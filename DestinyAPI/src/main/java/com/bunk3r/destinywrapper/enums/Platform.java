package com.bunk3r.destinywrapper.enums;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.bunk3r.destinywrapper.Constants;
import com.bunk3r.destinywrapper.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Platform {
    public static final int MISSING = -1;
    public static final int XBOX = 1;
    public static final int PSN = 2;

    private static final String XBOX_BUNGIE_NAME = "TigerXbox";
    private static final String PSN_BUNGIE_NAME = "TigerPSN";

    private static final String XBOX_LOGIN_MODIFIER = "Xuid";
    private static final String PSN_LOGIN_MODIFIER = "Psnid";

    @IntDef({PSN, XBOX, MISSING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
    }

    public static int resourceId(@VALUES int platform) {
        if (platform == MISSING) {
            return R.string.place_holder;
        }

        return (platform == PSN) ? R.string.psn_title : R.string.live_title;
    }

    public
    @NonNull
    static String name(@VALUES int platform) {
        if (platform == MISSING) {
            return "-";
        }

        return (platform == PSN) ? PSN_BUNGIE_NAME : XBOX_BUNGIE_NAME;
    }

    public
    @NonNull
    static String loginUrl(Context context, @VALUES int platform) {
        if (context == null || platform == MISSING) {
            return Constants.BUNGIE_404_URL;
        }

        String urlModifier = (platform == PSN) ? PSN_LOGIN_MODIFIER : XBOX_LOGIN_MODIFIER;
        return context.getString(R.string.log_in_url, urlModifier);
    }

}