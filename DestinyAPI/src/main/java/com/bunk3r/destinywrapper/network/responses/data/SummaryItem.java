package com.bunk3r.destinywrapper.network.responses.data;

import com.bunk3r.destinywrapper.models.ItemStat;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SummaryItem {

    @SerializedName("characterIndex")
    private int characterIndex;

    @SerializedName("itemHash")
    private long hash;

    @SerializedName("itemId")
    private String instanceId;

    @SerializedName("transferStatus")
    private int mTransferStatus;

    @SerializedName("itemLevel")
    private int level;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("qualityLevel")
    private int quality;

    @SerializedName("stats")
    private ItemStat[] stats;

    @SerializedName("primaryStat")
    private ItemStat primaryStat;

    @SerializedName("damageType")
    private int damageType;

    @SerializedName("isEquipment")
    private boolean equipment;

    @SerializedName("isGridComplete")
    private boolean maxOut;

    @SerializedName("locked")
    private boolean locked;

}