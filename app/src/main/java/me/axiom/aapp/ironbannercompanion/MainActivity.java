package me.axiom.aapp.ironbannercompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.security.Key;

import me.axiom.aapp.ironbannercompanion.api.DestinyAPI;
import me.axiom.aapp.ironbannercompanion.api.User;
import me.axiom.aapp.ironbannercompanion.api.responses.AccountSummaryResponse;
import me.axiom.aapp.ironbannercompanion.api.responses.MembershipIdResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    DestinyAPI destinyAPI;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DestinyAPI.initServer(this, "167b5904935a43cc9b188475002e7b1f");
        destinyAPI = DestinyAPI.getInstance();

        user.setGamerTag("Axiom1999");
        user.setPlatformId(1);

        TextView textView_Username = (TextView)findViewById(R.id.textView_Username);
        textView_Username.setText(getMembershipId());
    }

    public String getMembershipId() {

        destinyAPI.getMembershipId(user, new Callback<MembershipIdResponse>() {
            @Override
            public void onResponse(Response<MembershipIdResponse> response, Retrofit retrofit) {
                Log.d("API_CALL", "Response!");
                Log.d("API_CALL", response.body().getResponse());
                user.setMembershipId(response.body().getResponse());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("API_CALL", "Failure.");
                t.printStackTrace();
            }

        });
        return user.getMembershipId();
    }
}
