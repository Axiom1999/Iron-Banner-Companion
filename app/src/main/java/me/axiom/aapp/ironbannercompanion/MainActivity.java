package me.axiom.aapp.ironbannercompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import me.axiom.aapp.ironbannercompanion.api.DestinyAPI;
import me.axiom.aapp.ironbannercompanion.api.User;
import me.axiom.aapp.ironbannercompanion.api.responses.AccountSummaryResponse;
import me.axiom.aapp.ironbannercompanion.api.responses.MembershipIdResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    DestinyAPI destinyAPI;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DestinyAPI.initServer(this, "167b5904935a43cc9b188475002e7b1f");
        destinyAPI = DestinyAPI.getInstance();

        createUser("Axiom1999", 1);
        getMembershipId();

        TextView textView_Username = (TextView)findViewById(R.id.textView_Username);
        textView_Username.setText(user.getMembershipId());
    }

    public void createUser(String username, Integer platformId) {
        user = new User();

        user.setGamerTag(username);
        user.setPlatformId(platformId);
    }

    public void getMembershipId() {

        destinyAPI.getMembershipId(user, new Callback<MembershipIdResponse>() {
            @Override
            public void onResponse(Response<MembershipIdResponse> response, Retrofit retrofit) {
                Log.d("API_CALL", "Response!");
                user.setMembershipId(response.body().getResponse().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("API_CALL", "Failure.");
                t.printStackTrace();
            }
        });
    }
}
