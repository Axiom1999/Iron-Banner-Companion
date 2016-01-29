package me.axiom.aapp.ironbannercompanion.api.data;

import com.google.gson.annotations.SerializedName;

public class CharacterProgressionInfo {

    @SerializedName("progressions")
    private CharacterProgressions[] progressions;

    public CharacterProgressions[] getProgressions() {
        return progressions;
    }

}
