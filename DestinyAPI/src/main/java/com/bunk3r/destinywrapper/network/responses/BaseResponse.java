package com.bunk3r.destinywrapper.network.responses;

import com.bunk3r.destinywrapper.enums.ErrorCode;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
abstract public class BaseResponse<T> {

    @SerializedName("ErrorCode")
    private @ErrorCode.VALUES int mErrorCode;

    @SerializedName("ErrorStatus")
    private String mErrorStatus;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("ThrottleSeconds")
    private int mThrottleSeconds;

    @ErrorCode.VALUES
    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorStatus() {
        return mErrorStatus;
    }

    public String getMessage() {
        return mMessage;
    }

    public int getThrottleSeconds() {
        return mThrottleSeconds;
    }

    public abstract T getResponse();
}