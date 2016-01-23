package com.bunk3r.destinywrapper.network.requests;

import android.support.annotation.NonNull;

import com.bunk3r.destinywrapper.enums.Platform;
import com.bunk3r.destinywrapper.models.Guardian;
import com.bunk3r.destinywrapper.models.Item;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EquipItemsRequest extends ActionRequest {
    @SerializedName("itemIds")
    private final List<String> itemsInstanceId;

    public EquipItemsRequest(@Platform.VALUES int platform, @NonNull Guardian guardian, @NonNull List<Item> items) {
        super(platform, guardian);
        itemsInstanceId = new ArrayList<>(items.size());
        for (Item item : items) {
            itemsInstanceId.add(item.getInstanceId());
        }
    }
}