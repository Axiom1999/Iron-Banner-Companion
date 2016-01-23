package com.bunk3r.destinywrapper.network.responses.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountSummaryInfo {

    @SerializedName("items")
    private List<SummaryItem> items;

    @SerializedName("characters")
    private GuardianInfo[] characters;

    public List<SummaryItem> getItems() {
        return items;
    }

    public GuardianInfo[] getCharacters() {
        return characters;
    }

}