package com.bunk3r.destinywrapper.models;

import android.support.annotation.NonNull;

import com.bunk3r.destinywrapper.enums.Gender;
import com.bunk3r.destinywrapper.enums.GuardianClass;
import com.bunk3r.destinywrapper.enums.Race;
import com.bunk3r.destinywrapper.network.responses.data.CharacterBase;
import com.bunk3r.destinywrapper.network.responses.data.GuardianInfo;

import java.util.List;

public class Guardian {

    private String guardianId;

    private String emblemPath;

    private String backgroundPath;

    private int level;

    private int lightLevel;

    private float percentToNextLevel;

    private long currentActivityHash;

    @Gender.VALUES
    private int guardianGender;

    @Race.VALUES
    private long guardianRace;

    @GuardianClass.VALUES
    private int guardianClass;

    private List<Item> items;

    public Guardian() {
        // Default constructor needed by Realm
    }

    public Guardian(@NonNull GuardianInfo guardianInfo) {
        emblemPath = guardianInfo.getEmblemPath();
        backgroundPath = guardianInfo.getBackgroundPath();
        level = guardianInfo.getCharacterLevel();
        percentToNextLevel = guardianInfo.getPercentToNextLevel();

        CharacterBase characterBase = guardianInfo.getCharacterBase();
        guardianId = characterBase.getCharacterId();
        currentActivityHash = characterBase.getCurrentActivityHash();
        guardianGender = characterBase.getGenderType();
        guardianRace = characterBase.getRaceType();
        guardianClass = characterBase.getClassType();
    }

    // Getter's & Setter's
    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getEmblemPath() {
        return emblemPath;
    }

    public void setEmblemPath(String emblemPath) {
        this.emblemPath = emblemPath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
    }

    public float getPercentToNextLevel() {
        return percentToNextLevel;
    }

    public void setPercentToNextLevel(float percentToNextLevel) {
        this.percentToNextLevel = percentToNextLevel;
    }

    public long getCurrentActivityHash() {
        return currentActivityHash;
    }

    public void setCurrentActivityHash(long currentActivityHash) {
        this.currentActivityHash = currentActivityHash;
    }

    @Gender.VALUES
    public int getGuardianGender() {
        return guardianGender;
    }

    public void setGuardianGender(int guardianGender) {
        this.guardianGender = guardianGender;
    }

    @Race.VALUES
    public long getGuardianRace() {
        return guardianRace;
    }

    public void setGuardianRace(long guardianRace) {
        this.guardianRace = guardianRace;
    }

    @GuardianClass.VALUES
    public int getGuardianClass() {
        return guardianClass;
    }

    public void setGuardianClass(int guardianClass) {
        this.guardianClass = guardianClass;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}