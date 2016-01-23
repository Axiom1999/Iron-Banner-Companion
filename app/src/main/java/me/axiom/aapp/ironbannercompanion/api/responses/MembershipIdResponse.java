package me.axiom.aapp.ironbannercompanion.api.responses;

import com.google.gson.annotations.SerializedName;

import me.axiom.aapp.ironbannercompanion.api.BaseResponse;

public class MembershipIdResponse extends BaseResponse<String> {

    @SerializedName("Response")
    private String mResponse;

    @Override
    public String getResponse() {
        return mResponse;
    }

}
