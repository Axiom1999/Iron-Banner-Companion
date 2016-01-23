package com.bunk3r.destinywrapper.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

@SuppressWarnings("unused")
public class MembershipsResponse extends BaseResponse<Map<String, Integer>> {

    @SerializedName("Response")
    private Map<String, Integer> mResponse;

    @Override
    public Map<String, Integer> getResponse() {
        return mResponse;
    }

}