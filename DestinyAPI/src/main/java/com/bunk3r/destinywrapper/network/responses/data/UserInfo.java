package com.bunk3r.destinywrapper.network.responses.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserInfo {
    @SerializedName("membershipId")
    private String membershipId;

    @SerializedName("isDeleted")
    private boolean isDeleted;

    @SerializedName("psnDisplayName")
    private String psnDisplayName;

    @SerializedName("xboxDisplayName")
    private String xboxDisplayName;

    @SerializedName("profilePicturePath")
    private String profilePicturePath;

    @SerializedName("profileThemeName")
    private String profileThemeName;

    @SerializedName("grimoireScore")
    private int grimoireScore;

    @SerializedName("characters")
    private GuardianInfo[] characters;

    public String getMembershipId() {
        return membershipId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getPsnDisplayName() {
        return psnDisplayName;
    }

    public String getXboxDisplayName() {
        return xboxDisplayName;
    }

    public String getPicturePath() {
        return profilePicturePath;
    }

    public String getThemeName() {
        return profileThemeName;
    }

    public int getGrimoireScore() {
        return grimoireScore;
    }

    public GuardianInfo[] getCharacters() {
        return characters;
    }
}
