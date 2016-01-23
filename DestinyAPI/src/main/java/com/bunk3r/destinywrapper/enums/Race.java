package com.bunk3r.destinywrapper.enums;

import android.support.annotation.IntDef;

import com.bunk3r.destinywrapper.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Race {
    public static final long MISSING = -1;
    public static final long HUMAN = 3887404748L;
    public static final long AWOKEN = 2803282938L;
    public static final long EXO = 898834093L;

    private static final String HUMAN_RACE_NAME = "HUMAN";
    private static final String AWOKEN_RACE_NAME = "AWOKEN";
    private static final String EXO_RACE_NAME = "EXO";


    @IntDef({MISSING, HUMAN, AWOKEN, EXO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
    }

    public static String name(@VALUES long race) {
        final String name;

        if (race == HUMAN) {
            name = HUMAN_RACE_NAME;
        } else if (race == AWOKEN) {
            name = AWOKEN_RACE_NAME;
        } else if (race == EXO) {
            name = EXO_RACE_NAME;
        } else {
            name = "-";
        }

        return name;
    }

    public static int resourceId(@VALUES long race) {
        final int resId;

        if (race == HUMAN) {
            resId = R.string.race_human;
        } else if (race == AWOKEN) {
            resId = R.string.race_awoken;
        } else if (race == EXO) {
            resId = R.string.race_exo;
        } else {
            resId = R.string.place_holder;
        }

        return resId;
    }
}