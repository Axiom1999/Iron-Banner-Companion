package com.bunk3r.destinywrapper.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ErrorCode {
    public static final int SUCCESS = 1;
    public static final int USER_BANNED = 24;
    public static final int WAIT_MORE = 36;
    public static final int NEED_LOGIN = 99;
    public static final int ACCOUNT_NOT_FOUND = 1601;
    public static final int GUARDIAN_NOT_FOUND = 1620;
    public static final int ITEM_NOT_FOUND = 1623;
    public static final int ITEM_FAILED_LEVEL_CHECK = 1638;
    public static final int ITEM_FAILED_UNLOCK_CHECK = 1639;
    public static final int ITEM_UNEQUIPPABLE = 1640;
    public static final int ITEM_UNIQUE_EQUIP_RESTRICTED = 1641;
    public static final int ITEM_ACTION_FORBIDDEN = 1663;
//    public static final int CAN_ONLY_EQUIP_IN_GAME = MISSING;

    private static final String UNKNOWN_ERROR_NAME = "SOMETHING WENT WRONG";
    private static final String SUCCESS_ERROR_NAME = "SUCCESS";
    private static final String USER_BANNED_ERROR_NAME = "BANNED USER";
    private static final String WAIT_MORE_ERROR_NAME = "WAIT A LITTLE MORE";
    private static final String NEED_LOGIN_ERROR_NAME = "CREDENTIALS ARE OUTDATED";
    private static final String ACCOUNT_NOT_FOUND_ERROR_NAME = "ACCOUNT NOT FOUND";
    private static final String GUARDIAN_NOT_FOUND_ERROR_NAME = "GUARDIAN NOT FOUND";
    private static final String ITEM_NOT_FOUND_ERROR_NAME = "ITEM NOT FOUND";
    private static final String ITEM_LEVEL_CHECK_ERROR_NAME = "GUARDIAN NOT ENOUGH LEVEL";
    private static final String ITEM_UNLOCK_CHECK_ERROR_NAME = "CAN'T USE THIS ITEM";
    private static final String ITEM_UNEQUIPPABLE_ERROR_NAME = "ITEM CAN'T BE EQUIPPED";
    private static final String ITEM_UNIQUE_EQUIP_ERROR_NAME = "CAN ONLY HAVE ONE AT A TIME";
    private static final String ITEM_ACTION_FORBIDDEN_ERROR_NAME = "ACTION ON ITEM NOT POSSIBLE";

    @IntDef({SUCCESS, USER_BANNED, WAIT_MORE, NEED_LOGIN, ACCOUNT_NOT_FOUND, GUARDIAN_NOT_FOUND, ITEM_ACTION_FORBIDDEN, ITEM_FAILED_LEVEL_CHECK, ITEM_FAILED_UNLOCK_CHECK, ITEM_NOT_FOUND, ITEM_UNEQUIPPABLE, ITEM_UNIQUE_EQUIP_RESTRICTED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
    }

    public static String name(@VALUES int damageType) {
        final String name;

        switch (damageType) {
            case SUCCESS:
                name = SUCCESS_ERROR_NAME;
                break;
            case USER_BANNED:
                name = USER_BANNED_ERROR_NAME;
                break;
            case WAIT_MORE:
                name = WAIT_MORE_ERROR_NAME;
                break;
            case NEED_LOGIN:
                name = NEED_LOGIN_ERROR_NAME;
                break;
            case ACCOUNT_NOT_FOUND:
                name = ACCOUNT_NOT_FOUND_ERROR_NAME;
                break;
            case GUARDIAN_NOT_FOUND:
                name = GUARDIAN_NOT_FOUND_ERROR_NAME;
                break;
            case ITEM_NOT_FOUND:
                name = ITEM_NOT_FOUND_ERROR_NAME;
                break;
            case ITEM_FAILED_LEVEL_CHECK:
                name = ITEM_LEVEL_CHECK_ERROR_NAME;
                break;
            case ITEM_FAILED_UNLOCK_CHECK:
                name = ITEM_UNLOCK_CHECK_ERROR_NAME;
                break;
            case ITEM_UNEQUIPPABLE:
                name = ITEM_UNEQUIPPABLE_ERROR_NAME;
                break;
            case ITEM_UNIQUE_EQUIP_RESTRICTED:
                name = ITEM_UNIQUE_EQUIP_ERROR_NAME;
                break;
            case ITEM_ACTION_FORBIDDEN:
                name = ITEM_ACTION_FORBIDDEN_ERROR_NAME;
                break;
            default:
                name = UNKNOWN_ERROR_NAME;
        }

        return name;
    }
}