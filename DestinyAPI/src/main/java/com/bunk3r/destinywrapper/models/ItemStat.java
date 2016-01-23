package com.bunk3r.destinywrapper.models;

import com.google.gson.annotations.SerializedName;

public class ItemStat {

    @SerializedName("statHash")
    private long statHash;

    @SerializedName("value")
    private int value;

    @SerializedName("maximumValue")
    private int maxValue;

    // Getter's & Setter's
    public long getStatHash() {
        return statHash;
    }

    public void setStatHash(long statHash) {
        this.statHash = statHash;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}