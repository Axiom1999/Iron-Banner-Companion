package com.bunk3r.destinywrapper.network.requests;

import com.bunk3r.destinywrapper.enums.Platform;
import com.bunk3r.destinywrapper.models.Guardian;
import com.bunk3r.destinywrapper.models.Item;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TransferItemRequest extends ActionRequest {
    @SerializedName("itemId")
    private final String itemInstanceId;

    @SerializedName("itemReferenceHash")
    private final long itemId;

    @SerializedName("stackSize")
    private final int amountToTransfer;

    @SerializedName("transferToVault")
    private final boolean storeInVault;

    public static TransferItemRequest fromVault(@Platform.VALUES int platform, Guardian guardian, Item item, int amount) {
        return new TransferItemRequest(platform, guardian, item, amount, false);
    }

    public static TransferItemRequest toVault(@Platform.VALUES int platform, Guardian guardian, Item item, int amount) {
        return new TransferItemRequest(platform, guardian, item, amount, true);
    }

    private TransferItemRequest(@Platform.VALUES int platform, Guardian guardian, Item item, int amount, boolean toVault) {
        super(platform, guardian);
        itemInstanceId = item.getInstanceId();
        itemId = item.getHash();
        amountToTransfer = amount;
        storeInVault = toVault;
    }
}