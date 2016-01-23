package com.bunk3r.destinywrapper.utils;

import android.support.annotation.NonNull;

import com.bunk3r.destinywrapper.models.BaseItem;
import com.bunk3r.destinywrapper.models.Guardian;
import com.bunk3r.destinywrapper.models.User;

public final class ImageUtils {
    private static final String DESTINY_ASSETS_URL = "http://www.bungie.net";
    private static final String DESTINY_THEME_URL = "https://www.bungie.net/img/UserThemes/";
    private static final String DESTINY_THEME_HEADER_POSTFIX = "/header.jpg";

    private ImageUtils() {
        throw new UnsupportedOperationException("Don't call this constructor");
    }

    public static String getUrl(@NonNull BaseItem item) {
        return DESTINY_ASSETS_URL + item.getImagePath();
    }

    public static String getUrlEmblem(@NonNull Guardian guardian) {
        return DESTINY_ASSETS_URL + guardian.getEmblemPath();
    }

    public static String getUrlBackground(@NonNull Guardian guardian) {
        return DESTINY_ASSETS_URL + guardian.getBackgroundPath();
    }

    public static String getUrlProfile(@NonNull User user) {
        return DESTINY_THEME_URL + user.getProfilePicturePath();
    }

    public static String getUrlHeader(@NonNull User user) {
        return DESTINY_THEME_URL + user.getProfileThemeName() + DESTINY_THEME_HEADER_POSTFIX;
    }

}
