package com.bunk3r.destinywrapper.network.responses.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GuardianInfo {

    @SerializedName("emblemPath")
    private String emblemPath;

    @SerializedName("backgroundPath")
    private String backgroundPath;

    @SerializedName("characterLevel")
    private int characterLevel;

    @SerializedName("percentToNextLevel")
    private float percentToNextLevel;

    @SerializedName("characterBase")
    private CharacterBase characterBase;

    public String getEmblemPath() {
        return emblemPath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public float getPercentToNextLevel() {
        return percentToNextLevel;
    }

    public CharacterBase getCharacterBase() {
        return characterBase;
    }

    public int getGrimoireScore() {
        return characterBase.getGrimoireScore();
    }

}