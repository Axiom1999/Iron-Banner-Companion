package me.axiom.aapp.ironbannercompanion.api;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GuardianClass {

    public static final int TITAN = 0;
    public static final int HUNTER = 1;
    public static final int WARLOCK = 2;
    public static final int ANY = 3;

    private static final String TITAN_CLASS_NAME = "TITAN";
    private static final String HUNTER_CLASS_NAME = "HUNTER";
    private static final String WARLOCK_CLASS_NAME = "WARLOCK";
    private static final String ANY_CLASS_NAME = "ANY";


    @IntDef({TITAN, HUNTER, WARLOCK, ANY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
        //
    }

    public static String name(@VALUES int guardianClass) {
        final String name;

        switch (guardianClass) {
            case TITAN:
                name = TITAN_CLASS_NAME;
                break;
            case HUNTER:
                name = HUNTER_CLASS_NAME;
                break;
            case WARLOCK:
                name = WARLOCK_CLASS_NAME;
                break;
            default:
                name = ANY_CLASS_NAME;
        }

        return name;
    }

    public static int resourceId(@VALUES int guardianClass) {
        final int resId;

        switch (guardianClass) {
            case TITAN:
                resId = 0;
                break;
            case HUNTER:
                resId = 1;
                break;
            case WARLOCK:
                resId = 2;
                break;
            default:
                resId = 3;
        }

        return resId;
    }

}
