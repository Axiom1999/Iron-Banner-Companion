package me.axiom.aapp.ironbannercompanion.api.data;

import com.google.gson.annotations.SerializedName;

public class AccountSummaryData {

    @SerializedName("data")
    private AccountSummaryInfo data;

    public AccountSummaryInfo getData() {
        return data;
    }

}
