package com.bunk3r.destinywrapper.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item extends BaseItem {
    private static final int EQUIPPED = 1;
    private static final int NON_TRANSFERABLE = 2;

    @SerializedName("itemId")
    private String instanceId;

    @SerializedName("transferStatus")
    private int transferStatus;

    @SerializedName("itemLevel")
    private int level;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("qualityLevel")
    private int quality;

    @SerializedName("stats")
    private List<ItemStat> stats;

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

    public Item() {
    }

    public Item(@NonNull Item itemToClone) {
        super(itemToClone);
        instanceId = itemToClone.instanceId;
        transferStatus = itemToClone.transferStatus;
        level = itemToClone.level;
        quantity = itemToClone.quantity;
        quality = itemToClone.quality;
        stats = itemToClone.stats;
        primaryStat = itemToClone.primaryStat;
        damageType = itemToClone.damageType;
        equipment = itemToClone.equipment;
        maxOut = itemToClone.maxOut;
        locked = itemToClone.locked;
    }

    public static boolean isTransferable(@NonNull Item item) {
        return (item.getTransferStatus() & NON_TRANSFERABLE) == 0;
    }

    public static boolean isEquipped(@NonNull Item item) {
        return (item.getTransferStatus() & EQUIPPED) != 0;
    }

    public static void increaseAmountBy(@NonNull Item item, int amount) {
        item.setQuantity(item.getQuantity() + amount);
    }

    public static void decreaseAmountBy(@NonNull Item item, int amount) {
        increaseAmountBy(item, -amount);
    }

    public static void toggleIsEquipped(@NonNull Item item) {
        item.setTransferStatus(item.getTransferStatus() ^ EQUIPPED);
    }

    // Getters % Setters
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public int getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(int transferStatus) {
        this.transferStatus = transferStatus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public List<ItemStat> getStats() {
        return stats;
    }

    public void setStats(List<ItemStat> stats) {
        this.stats = stats;
    }

    public ItemStat getPrimaryStat() {
        return primaryStat;
    }

    public void setPrimaryStat(ItemStat primaryStat) {
        this.primaryStat = primaryStat;
    }

    public int getDamageType() {
        return damageType;
    }

    public void setDamageType(int damageType) {
        this.damageType = damageType;
    }

    public boolean isEquipment() {
        return equipment;
    }

    public void setEquipment(boolean equipment) {
        this.equipment = equipment;
    }

    public boolean isMaxOut() {
        return maxOut;
    }

    public void setMaxOut(boolean maxOut) {
        this.maxOut = maxOut;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}