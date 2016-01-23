package com.bunk3r.destinywrapper.network.responses.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AccountSummaryData {

    @SerializedName("data")
    private AccountSummaryInfo data;

    public AccountSummaryInfo getData() {
        return data;
    }
}
