package me.axiom.aapp.ironbannercompanion.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

import me.axiom.aapp.ironbannercompanion.api.BaseResponse;

public class MembershipIdResponse extends BaseResponse<Map<String, Integer>> {

    @SerializedName("Response")
    private Map<String, Integer> mResponse;

    @Override
    public Map<String, Integer> getResponse() {
        return mResponse;
    }

}
