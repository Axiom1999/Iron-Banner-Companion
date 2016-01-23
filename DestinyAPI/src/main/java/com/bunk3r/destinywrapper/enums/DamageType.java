package com.bunk3r.destinywrapper.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DamageType {
    public static final int NONE = 0;
    public static final int KINETIC = 1;
    public static final int ARC = 2;
    public static final int SOLAR = 3;
    public static final int VOID = 4;

    private static final String KINETIC_DAMAGE_NAME = "KINETIC";
    private static final String ARC_DAMAGE_NAME = "ARC";
    private static final String SOLAR_DAMAGE_NAME = "SOLAR";
    private static final String VOID_DAMAGE_NAME = "VOID";

    @IntDef({NONE, KINETIC, ARC, SOLAR, VOID})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
    }

    public static String name(@VALUES int damageType) {
        final String name;

        switch (damageType) {
            case KINETIC:
                name = KINETIC_DAMAGE_NAME;
                break;
            case ARC:
                name = ARC_DAMAGE_NAME;
                break;
            case SOLAR:
                name = SOLAR_DAMAGE_NAME;
                break;
            case VOID:
                name = VOID_DAMAGE_NAME;
                break;
            default:
                name = "-";
        }

        return name;
    }

    public static int color(@VALUES int damageType) {
        final int color;

        switch (damageType) {
            case ARC:
                color = 0xFF85C5EC;
                break;
            case SOLAR:
                color = 0xFFF2721B;
                break;
            case VOID:
                color = 0xFFB184C5;
                break;
            default:
                color = 0xFFFFFFFF;
        }

        return color;
    }
}