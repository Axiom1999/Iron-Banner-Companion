package com.bunk3r.destinywrapper.enums;

import android.support.annotation.IntDef;

import com.bunk3r.destinywrapper.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Gender {
    public static final int MALE = 0;
    public static final int FEMALE = 1;

    private static final String MALE_GENDER_NAME = "MALE";
    private static final String FEMALE_GENDER_NAME = "FEMALE";

    @IntDef({MALE, FEMALE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
    }

    public static String name(@VALUES int gender) {
        return (gender == MALE) ? MALE_GENDER_NAME : FEMALE_GENDER_NAME;
    }

    public static int resourceId(@VALUES int gender) {
        return (gender == MALE) ? R.string.gender_male : R.string.gender_female;
    }
}