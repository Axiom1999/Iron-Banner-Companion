package me.axiom.aapp.ironbannercompanion.api.data;

import com.google.gson.annotations.SerializedName;

public class AccountSummaryInfo {

    @SerializedName("characters")
    private GuardianInfo[] characters;

    public GuardianInfo[] getCharacters() {
        return characters;
    }

}
