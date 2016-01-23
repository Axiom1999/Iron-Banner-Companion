package me.axiom.aapp.ironbannercompanion.api.responses;

import com.google.gson.annotations.SerializedName;

import me.axiom.aapp.ironbannercompanion.api.BaseResponse;
import me.axiom.aapp.ironbannercompanion.api.data.AccountSummaryData;

public class AccountSummaryResponse extends BaseResponse<AccountSummaryData> {

    @SerializedName("Response")
    private AccountSummaryData mResponse;

    public AccountSummaryData getResponse() {
        return mResponse;
    }

}
