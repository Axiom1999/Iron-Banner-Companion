package com.bunk3r.destinywrapper.network.responses;

import com.bunk3r.destinywrapper.network.responses.data.EquipItemsResult;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class EquipItemsResponse extends BaseResponse<EquipItemsResult> {
    @SerializedName("Response")
    private EquipItemsResult mResponse;

    @Override
    public EquipItemsResult getResponse() {
        return mResponse;
    }
}