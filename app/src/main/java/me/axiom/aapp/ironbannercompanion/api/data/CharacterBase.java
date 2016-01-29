package me.axiom.aapp.ironbannercompanion.api.data;

import com.google.gson.annotations.SerializedName;

import me.axiom.aapp.ironbannercompanion.api.GuardianClass;

public class CharacterBase {

    @SerializedName("characterId")
    private String characterId;

    @SerializedName("classType")
    @GuardianClass.VALUES
    private int classType;

    @SerializedName("powerLevel")
    private int powerLevel;

    public String getCharacterId() {
        return characterId;
    }

    @GuardianClass.VALUES
    public int getClassType() {
        return classType;
    }

    public int getLightLevel() {
        return powerLevel;
    }

}
