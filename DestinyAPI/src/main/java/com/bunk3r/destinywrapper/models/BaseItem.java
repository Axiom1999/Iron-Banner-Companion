package com.bunk3r.destinywrapper.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BaseItem {
    @SerializedName("itemHash")
    private long hash;

    @SerializedName("name")
    private String name;

    @SerializedName("icon")
    private String imagePath;

    @SerializedName("bucket")
    private long bucket;

    @SerializedName("tier")
    private int tier;

    @SerializedName("type")
    private String type;

    @SerializedName("class")
    private int guardianClass;

    @SerializedName("maxStackSize")
    private int maxStackSize;

    public BaseItem() {

    }

    public BaseItem(@NonNull BaseItem itemToClone) {
        setDefinition(itemToClone);
    }

    public void setDefinition(@NonNull BaseItem itemToClone) {
        hash = itemToClone.hash;
        name = itemToClone.name;
        imagePath = itemToClone.imagePath;
        bucket = itemToClone.bucket;
        tier = itemToClone.tier;
        type = itemToClone.type;
        guardianClass = itemToClone.guardianClass;
        maxStackSize = itemToClone.maxStackSize;
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getBucket() {
        return bucket;
    }

    public void setBucket(long bucket) {
        this.bucket = bucket;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGuardianClass() {
        return guardianClass;
    }

    public void setGuardianClass(int guardianClass) {
        this.guardianClass = guardianClass;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }
}