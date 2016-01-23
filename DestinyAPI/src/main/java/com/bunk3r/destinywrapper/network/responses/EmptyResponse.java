package com.bunk3r.destinywrapper.network.responses;

public class EmptyResponse extends BaseResponse<Void> {
    @Override
    public Void getResponse() {
        return null;
    }
}
