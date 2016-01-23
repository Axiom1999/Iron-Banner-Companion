package com.bunk3r.destinywrapper.enums;

import android.support.annotation.IntDef;

import com.bunk3r.destinywrapper.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ItemTier {
    public static final int NONE = 0;
    public static final int BASIC = 1;
    public static final int COMMON = 2;
    public static final int UNCOMMON = 3;
    public static final int RARE = 4;
    public static final int LEGENDARY = 5;
    public static final int EXOTIC = 6;

    @IntDef({NONE, BASIC, COMMON, UNCOMMON, RARE, LEGENDARY, EXOTIC})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
    }


    // TODO: Change for the real color later on
    public static int colorResourceId(@VALUES int tier) {
        final int resId;

        switch (tier) {
            case UNCOMMON:
                resId = R.string.class_titan;
                break;
            case RARE:
                resId = R.string.class_hunter;
                break;
            case LEGENDARY:
                resId = R.string.class_warlock;
                break;
            case EXOTIC:
                resId = R.string.class_titan;
                break;
            default:
                resId = R.string.class_all;
        }

        return resId;
    }
}