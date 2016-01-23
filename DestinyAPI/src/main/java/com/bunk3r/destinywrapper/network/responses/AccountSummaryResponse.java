package com.bunk3r.destinywrapper.network.responses;

import com.bunk3r.destinywrapper.network.responses.data.AccountSummaryData;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AccountSummaryResponse extends BaseResponse<AccountSummaryData> {

    @SerializedName("Response")
    private AccountSummaryData mResponse;

    @Override
    public AccountSummaryData getResponse() {
        return mResponse;
    }

}
