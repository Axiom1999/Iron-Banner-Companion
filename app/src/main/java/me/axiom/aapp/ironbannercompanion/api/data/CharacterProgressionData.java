package me.axiom.aapp.ironbannercompanion.api.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterProgressionData {

    @SerializedName("data")
    private CharacterProgressionInfo progressions;

    public CharacterProgressionInfo getData() {
        return progressions;
    }

}
