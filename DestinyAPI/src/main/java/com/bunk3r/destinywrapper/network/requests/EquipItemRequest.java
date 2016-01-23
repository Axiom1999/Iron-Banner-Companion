package com.bunk3r.destinywrapper.network.requests;

import android.support.annotation.NonNull;

import com.bunk3r.destinywrapper.enums.Platform;
import com.bunk3r.destinywrapper.models.Guardian;
import com.bunk3r.destinywrapper.models.Item;
import com.google.gson.annotations.SerializedName;

public class EquipItemRequest extends ActionRequest {
    @SerializedName("itemId")
    private final String itemInstanceId;

    public EquipItemRequest(@Platform.VALUES int platform,@NonNull Guardian guardian,@NonNull Item item) {
        super(platform, guardian);
        itemInstanceId = item.getInstanceId();
    }
}