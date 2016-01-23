package com.bunk3r.destinywrapper.network.endpoints;

import com.bunk3r.destinywrapper.network.requests.EquipItemRequest;
import com.bunk3r.destinywrapper.network.requests.EquipItemsRequest;
import com.bunk3r.destinywrapper.network.requests.TransferItemRequest;
import com.bunk3r.destinywrapper.network.responses.AccountSummaryResponse;
import com.bunk3r.destinywrapper.network.responses.EquipItemResponse;
import com.bunk3r.destinywrapper.network.responses.TransferItemResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface InventoryEndpoint {
    @GET("/Platform/Destiny/{platId}/Account/{accountId}/Items/")
    Call<AccountSummaryResponse> getAccountSummary(@Path("platId") int platformId,
                                                   @Path("accountId") String membershipId);

    @POST("/Platform/Destiny/EquipItem/")
    Call<EquipItemResponse> equipItem(@Body EquipItemRequest request);

    @POST("/Platform/Destiny/EquipItems/")
    Call<EquipItemResponse> equipItems(@Body EquipItemsRequest request);

    @POST("/Platform/Destiny/TransferItem/")
    Call<TransferItemResponse> transferItem(@Body TransferItemRequest request);
}