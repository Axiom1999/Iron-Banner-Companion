package com.bunk3r.destinywrapper.enums;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ItemType {
    /* SUBCLASS */
    public static final String HUNTER_SUBCLASS = "Hunter Subclass";
    public static final String WARLOCK_SUBCLASS = "Warlock Subclass";
    public static final String TITAN_SUBCLASS = "Titan Subclass";

    /* WEAPONS */
    public static final String HAND_CANNON = "Hand Cannon";
    public static final String AUTO_RIFLE = "Auto Rifle";
    public static final String PULSE_RIFLE = "Pulse Rifle";
    public static final String SCOUT_RIFLE = "Scout Rifle";
    public static final String SIDEARM = "Sidearm";
    public static final String SHOTGUN = "Shotgun";
    public static final String FUSION_RIFLE = "Fusion Rifle";
    public static final String SNIPER_RIFLE = "Sniper Rifle";
    public static final String MACHINE_GUN = "Machine Gun";
    public static final String ROCKET_LAUNCHER = "Rocket Launcher";
    public static final String SWORD = "Sword";
    public static final String PRIMARY_WEAPON_ENGRAM = "Primary Weapon Engram";
    public static final String SPECIAL_WEAPON_ENGRAM = "Special Weapon Engram";
    public static final String HEAVY_WEAPON_ENGRAM = "Heavy Weapon Engram";
    public static final String ARMSDAY_ORDER = "Armsday Order";

    /* ARMOR */
    public static final String HELMET = "Helmet";
    public static final String GAUNTLETS = "Gauntlets";
    public static final String CHEST_ARMOR = "Chest Armor";
    public static final String LEG_ARMOR = "Leg Armor";
    public static final String TITAN_MARK = "Titan Mark";
    public static final String HUNTER_CLOAK = "Hunter Cloak";
    public static final String WARLOCK_BOND = "Warlock Bond";
    public static final String HUNTER_ARTIFACT = "Hunter Artifact";
    public static final String WARLOCK_ARTIFACT = "Warlock Artifact";
    public static final String TITAN_ARTIFACT = "Titan Artifact";
    public static final String HELMET_ENGRAM = "Helmet Engram";
    public static final String GAUNTLET_ENGRAM = "Gauntlet Engram";
    public static final String BODY_ARMOR_ENGRAM = "Body Armor Engram";
    public static final String LEG_ARMOR_ENGRAM = "Leg Armor Engram";
    public static final String CLASS_ITEM_ENGRAM = "Class Item Engram";

    /* Customization */
    public static final String GHOST_SHELL = "Ghost Shell";
    public static final String VEHICLE = "Vehicle";
    public static final String SHIP = "Ship";
    public static final String SHIP_SCHEMATICS = "Ship Schematics";
    public static final String ARMOR_SHADER = "Armor Shader";
    public static final String RESTORE_DEFAULTS = "Restore Defaults";
    public static final String EMBLEM = "Emblem";
    public static final String EMOTE = "Emote";

    /* GENERAL */
    public static final String CURRENCY = "Currency";
    public static final String MESSAGE_FROM_TESS = "Message from Tess";
    public static final String MESSAGE_FROM_ZAVALA = "Message from Zavala";
    public static final String MESSAGE_FROM_IKORA_REY = "Message from Ikora Rey";
    public static final String MESSAGE_FROM_CAYDE6 = "Message from Cayde-6";
    public static final String MESSAGE_FROM_BUNGIE = "Message from Bungie";
    public static final String MATERIAL_EXCHANGE = "Material Exchange";
    public static final String COMMENDATION = "Commendation";
    public static final String INVITATION = "Invitation";
    public static final String TRIALS_REWARD = "Trials Reward";
    public static final String BUFF = "Buff";
    public static final String CURIO = "Curio";
    public static final String DAILY_REWARD = "Daily Reward";
    public static final String CORRUPTED_ENGRAM = "Corrupted Engrams";
    public static final String ENGRAM = "Engram";
    public static final String CAMERA = "Camera";
    public static final String PACKAGE = "Package";

    /* CONSUMABLES */
    public static final String CONSUMABLE = "Consumable";
    public static final String MATERIAL = "Material";
    public static final String VEHICLE_UPGRADE = "Vehicle Upgrade";

    @StringDef({HUNTER_SUBCLASS, WARLOCK_SUBCLASS, TITAN_SUBCLASS, HAND_CANNON, AUTO_RIFLE, PULSE_RIFLE, SCOUT_RIFLE, SIDEARM, SHOTGUN, FUSION_RIFLE, SNIPER_RIFLE, MACHINE_GUN, ROCKET_LAUNCHER, SWORD, PRIMARY_WEAPON_ENGRAM, SPECIAL_WEAPON_ENGRAM, HEAVY_WEAPON_ENGRAM, ARMSDAY_ORDER, HELMET, GAUNTLETS, CHEST_ARMOR, LEG_ARMOR, TITAN_MARK, HUNTER_CLOAK, WARLOCK_BOND, HUNTER_ARTIFACT, WARLOCK_ARTIFACT, TITAN_ARTIFACT, HELMET_ENGRAM, GAUNTLET_ENGRAM, BODY_ARMOR_ENGRAM, LEG_ARMOR_ENGRAM, CLASS_ITEM_ENGRAM, GHOST_SHELL, VEHICLE, SHIP, SHIP_SCHEMATICS, ARMOR_SHADER, RESTORE_DEFAULTS, EMBLEM, EMOTE, CURRENCY, MESSAGE_FROM_TESS, MESSAGE_FROM_ZAVALA, MESSAGE_FROM_IKORA_REY, MESSAGE_FROM_CAYDE6, MESSAGE_FROM_BUNGIE, MATERIAL_EXCHANGE, COMMENDATION, INVITATION, TRIALS_REWARD, BUFF, CURIO, DAILY_REWARD, CORRUPTED_ENGRAM, ENGRAM, CAMERA, PACKAGE, CONSUMABLE, MATERIAL, VEHICLE_UPGRADE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VALUES {
    }

    public static boolean isNonEquippable(@NonNull @VALUES String itemType) {
        switch (itemType) {
            case ENGRAM:
            case CORRUPTED_ENGRAM:
            case PRIMARY_WEAPON_ENGRAM:
            case SPECIAL_WEAPON_ENGRAM:
            case HEAVY_WEAPON_ENGRAM:
            case HELMET_ENGRAM:
            case BODY_ARMOR_ENGRAM:
            case GAUNTLET_ENGRAM:
            case LEG_ARMOR_ENGRAM:
            case CLASS_ITEM_ENGRAM:
                // Non-Related to weapons or armor
            case ARMSDAY_ORDER:
            case CONSUMABLE:
            case MATERIAL:
            case VEHICLE_UPGRADE:
            case PACKAGE:
            case CAMERA:
            case DAILY_REWARD:
            case CURIO:
            case BUFF:
            case TRIALS_REWARD:
            case INVITATION:
            case COMMENDATION:
            case MATERIAL_EXCHANGE:
            case CURRENCY:
            case MESSAGE_FROM_BUNGIE:
            case MESSAGE_FROM_CAYDE6:
            case MESSAGE_FROM_IKORA_REY:
            case MESSAGE_FROM_TESS:
            case MESSAGE_FROM_ZAVALA:
            case SHIP_SCHEMATICS:
                return true;
            default:
                return false;
        }
    }
}