package com.bunk3r.destinywrapper.network.responses.data;

import com.bunk3r.destinywrapper.enums.Platform;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AccountInfo {
    @SerializedName("user")
    private UserInfo user;

    @SerializedName("email")
    private String email;

    @SerializedName("psnId")
    private String psnId;

    @SerializedName("gamerTag")
    private String liveId;

    public UserInfo getUserInfo() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getGamerTag() {
        return (psnId != null) ? psnId : liveId;
    }

    @Platform.VALUES
    public int getPlatform() {
        if (psnId != null) {
            return Platform.PSN;
        }

        return Platform.XBOX;
    }
}