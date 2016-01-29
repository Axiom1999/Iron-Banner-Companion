package me.axiom.aapp.ironbannercompanion.api.data;

import com.google.gson.annotations.SerializedName;

public class CharacterProgressions {

    @SerializedName("progressionHash")
    private long progressionHash;

    @SerializedName("level")
    private int level;

    @SerializedName("progressToNextLevel")
    private int progressToNextLevel;

    @SerializedName("nextLevelAt")
    private int nextLevelAt;

    public long getProgressionHash() {
        return progressionHash;
    }

    public int getLevel() {
        return level;
    }

    public int getProgressToNextLevel() {
        return progressToNextLevel;
    }

    public int getNextLevelAt() {
        return nextLevelAt;
    }

}
