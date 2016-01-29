package me.axiom.aapp.ironbannercompanion.api;

import me.axiom.aapp.ironbannercompanion.api.responses.AccountSummaryResponse;
import me.axiom.aapp.ironbannercompanion.api.responses.CharacterProgressionResponse;
import me.axiom.aapp.ironbannercompanion.api.responses.MembershipIdResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface Endpoints {

    @GET("/Platform/Destiny/{platform}/Account/{membershipId}/Summary")
    Call<AccountSummaryResponse> getAccountInfo(@Path("platform") int platformId, @Path("membershipId") String membershipId);

    @GET("/Platform/Destiny/{platform}/Stats/GetMembershipIdByDisplayName/{username}")
    Call<MembershipIdResponse> getMembershipId(@Path("platform") int platformId, @Path("username") String username);

    @GET("/Platform/Destiny/{platform}/Account/{membershipId}/Character/{characterId}/Progression")
    Call<CharacterProgressionResponse> getCharacterProgression(@Path("platform") int platformId, @Path("membershipId") String membershipId, @Path("characterId") String characterId);

}
