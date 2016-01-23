package me.axiom.aapp.ironbannercompanion.api;

import android.support.annotation.NonNull;

import me.axiom.aapp.ironbannercompanion.api.data.CharacterBase;
import me.axiom.aapp.ironbannercompanion.api.data.GuardianInfo;

public class Guardian {

    private String guardianId;

    private String emblemPath;

    private String backgroundPath;

    @GuardianClass.VALUES
    private int guardianClass;

    public Guardian() {
        // Default constructor needed by Realm
    }

    public Guardian(@NonNull GuardianInfo guardianInfo) {
        emblemPath = guardianInfo.getEmblemPath();
        backgroundPath = guardianInfo.getBackgroundPath();

        CharacterBase characterBase = guardianInfo.getCharacterBase();
        guardianId = characterBase.getCharacterId();
        guardianClass = characterBase.getClassType();
    }

    // Getter's & Setter's
    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getEmblemPath() {
        return emblemPath;
    }

    public void setEmblemPath(String emblemPath) {
        this.emblemPath = emblemPath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    @GuardianClass.VALUES
    public int getGuardianClass() {
        return guardianClass;
    }

    public void setGuardianClass(int guardianClass) {
        this.guardianClass = guardianClass;
    }

}
