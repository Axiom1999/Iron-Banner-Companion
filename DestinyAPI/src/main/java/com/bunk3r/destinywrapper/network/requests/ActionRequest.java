package com.bunk3r.destinywrapper.network.requests;

import android.support.annotation.NonNull;

import com.bunk3r.destinywrapper.enums.Platform;
import com.bunk3r.destinywrapper.models.Guardian;
import com.google.gson.annotations.SerializedName;

public abstract class ActionRequest {
    @Platform.VALUES
    @SerializedName("membershipType")
    private final int platformId;

    @SerializedName("characterId")
    private final String guardian;

    public ActionRequest(@Platform.VALUES int platform, @NonNull Guardian guardian) {
        platformId = platform;
        this.guardian = guardian.getGuardianId();
    }
}