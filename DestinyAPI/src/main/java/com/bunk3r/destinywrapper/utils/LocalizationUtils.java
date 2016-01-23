package com.bunk3r.destinywrapper.utils;

import java.util.Locale;

public final class LocalizationUtils {
    private static final String PORTUGUESE = "pt";
    private static final String VALID_PORTUGUESE = "pt-br";

    private LocalizationUtils() {
        throw new UnsupportedOperationException("Don't call this constructor");
    }

    public static String getLocale() {
        String locale = Locale.getDefault().getLanguage();

        if (PORTUGUESE.equalsIgnoreCase(locale)) {
            locale = VALID_PORTUGUESE;
        }

        return locale;
    }
}