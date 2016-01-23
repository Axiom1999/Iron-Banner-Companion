package me.axiom.aapp.ironbannercompanion.api.data;

import com.google.gson.annotations.SerializedName;

public class GuardianInfo {

    @SerializedName("emblemPath")
    private String emblemPath;

    @SerializedName("backgroundPath")
    private String backgroundPath;

    @SerializedName("characterBase")
    private CharacterBase characterBase;

    public String getEmblemPath() {
        return emblemPath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public CharacterBase getCharacterBase() {
        return characterBase;
    }

}
