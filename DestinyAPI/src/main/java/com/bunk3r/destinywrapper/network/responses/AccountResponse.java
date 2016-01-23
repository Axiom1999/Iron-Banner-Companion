package com.bunk3r.destinywrapper.network.responses;

import com.bunk3r.destinywrapper.network.responses.data.AccountInfo;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AccountResponse extends BaseResponse<AccountInfo> {

    @SerializedName("Response")
    private AccountInfo mResponse;

    @Override
    public AccountInfo getResponse() {
        return mResponse;
    }

}