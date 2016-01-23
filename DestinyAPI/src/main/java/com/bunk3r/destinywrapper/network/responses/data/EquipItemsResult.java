package com.bunk3r.destinywrapper.network.responses.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EquipItemsResult {
    @SerializedName("equipResults")
    private List<Entry> equipResults;

    public static class Entry {
        @SerializedName("itemInstanceId")
        private String itemInstanceId;

        @SerializedName("equipStatus")
        private int equipStatus;
    }
}