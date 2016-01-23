package com.bunk3r.destinywrapper.network.responses.data;

import com.bunk3r.destinywrapper.enums.Gender;
import com.bunk3r.destinywrapper.enums.GuardianClass;
import com.bunk3r.destinywrapper.enums.Race;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CharacterBase {

    @SerializedName("characterId")
    private String characterId;

    @SerializedName("powerLevel")
    private int powerLevel;

    @SerializedName("currentActivityHash")
    private long currentActivityHash;

    @SerializedName("genderType")
    @Gender.VALUES
    private int genderType;

    @SerializedName("raceHash")
    @Race.VALUES
    private long raceType;

    @SerializedName("classType")
    @GuardianClass.VALUES
    private int classType;

    @SerializedName("grimoireScore")
    private int grimoireScore;

    public String getCharacterId() {
        return characterId;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public long getCurrentActivityHash() {
        return currentActivityHash;
    }

    @Gender.VALUES
    public int getGenderType() {
        return genderType;
    }

    @Race.VALUES
    public long getRaceType() {
        return raceType;
    }

    @GuardianClass.VALUES
    public int getClassType() {
        return classType;
    }

    public int getGrimoireScore() {
        return grimoireScore;
    }
}