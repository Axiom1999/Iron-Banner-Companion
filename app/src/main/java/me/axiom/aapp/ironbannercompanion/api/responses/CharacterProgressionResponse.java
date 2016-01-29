package me.axiom.aapp.ironbannercompanion.api.responses;

import com.google.gson.annotations.SerializedName;

import me.axiom.aapp.ironbannercompanion.api.BaseResponse;
import me.axiom.aapp.ironbannercompanion.api.data.CharacterProgressionData;

public class CharacterProgressionResponse extends BaseResponse<CharacterProgressionData> {

    @SerializedName("Response")
    private CharacterProgressionData mResponse;

    public CharacterProgressionData getResponse() {
        return mResponse;
    }

}
