package com.bunk3r.destinywrapper.network.endpoints;

import com.bunk3r.destinywrapper.network.responses.AccountResponse;
import com.bunk3r.destinywrapper.network.responses.MembershipsResponse;

import retrofit.Call;
import retrofit.http.GET;

public interface UserEndpoint {

    @GET("/Platform/User/GetBungieNetUser/")
    Call<AccountResponse> getAccountInfo();

    @GET("/Platform/User/GetMembershipIds")
    Call<MembershipsResponse> getMemberships();

}