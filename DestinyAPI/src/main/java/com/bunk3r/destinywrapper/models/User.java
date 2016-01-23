package com.bunk3r.destinywrapper.models;

import com.bunk3r.destinywrapper.enums.Platform;

import java.util.List;

public class User {

    private String membershipId;

    private String cookies;

    private String gamerTag;

    @Platform.VALUES
    private int platformId;

    private String profilePicturePath;

    private String profileThemeName;

    private int grimoireScore;

    private List<Guardian> guardians;

    // Getter's & Setter's
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    @Platform.VALUES
    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getProfileThemeName() {
        return profileThemeName;
    }

    public void setProfileThemeName(String profileThemeName) {
        this.profileThemeName = profileThemeName;
    }

    public int getGrimoireScore() {
        return grimoireScore;
    }

    public void setGrimoireScore(int grimoireScore) {
        this.grimoireScore = grimoireScore;
    }

    public List<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(List<Guardian> guardians) {
        this.guardians = guardians;
    }
}