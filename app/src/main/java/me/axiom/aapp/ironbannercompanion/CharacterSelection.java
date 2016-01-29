package me.axiom.aapp.ironbannercompanion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import me.axiom.aapp.ironbannercompanion.api.DestinyAPI;
import me.axiom.aapp.ironbannercompanion.api.Guardian;
import me.axiom.aapp.ironbannercompanion.api.User;
import me.axiom.aapp.ironbannercompanion.api.data.GuardianInfo;
import me.axiom.aapp.ironbannercompanion.api.responses.AccountSummaryResponse;
import pl.droidsonroids.gif.GifImageView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CharacterSelection extends AppCompatActivity {

    private SharedPreferences prefs;

    private String username, membershipId;
    private int platformId;

    private DestinyAPI destinyAPI;
    private User user;

    private Guardian guardian_ONE, guardian_TWO, guardian_THREE;

    private Intent intent_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);


        prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        username = prefs.getString("username", "");
        platformId = prefs.getInt("platformId", 0);
        membershipId = prefs.getString("membershipId", "");

        DestinyAPI.initServer(this, "167b5904935a43cc9b188475002e7b1f");
        destinyAPI = DestinyAPI.getInstance();

        createUser(username, platformId, membershipId);

        Button button_Change_User = (Button) findViewById(R.id.button_Change_User);

        button_Change_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), LoginPlatformActivity.class);
                startActivity(intent);
                finish();

            }
        });

        setupListView();

    }

    public void createUser(String u, int p, String m) {

        user = new User();
        user.setGamerTag(u);
        user.setPlatformId(p);
        user.setMembershipId(m);

    }

    public void setupListView() {

        destinyAPI.getAccountInformation(user, new Callback<AccountSummaryResponse>() {
            @Override
            public void onResponse(Response<AccountSummaryResponse> response, Retrofit retrofit) {

                final GuardianInfo[] guardians = response.body().getResponse().getData().getCharacters();

                GifImageView gifImageView = (GifImageView) findViewById(R.id.gifImageView_Loader);
                gifImageView.setVisibility(View.GONE);

                ListViewAdapter adapter = new ListViewAdapter(getBaseContext(), R.layout.listview_characters_content, guardians);

                ListView listView_Characters = (ListView) findViewById(R.id.listView_Characters);
                listView_Characters.setAdapter(adapter);

                listView_Characters.setClickable(true);

                listView_Characters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getBaseContext(), MainActivity.class);

                        intent.putExtra("characterId", guardians[position].getCharacterBase().getCharacterId());
                        intent.putExtra("username", username);
                        intent.putExtra("membershipId", membershipId);
                        intent.putExtra("platformId", platformId);

                        startActivity(intent);
                        finish();

                    }
                });

            }

            @Override
            public void onFailure(Throwable t) {

                Toast.makeText(CharacterSelection.this, "Unable to load character!", Toast.LENGTH_SHORT).show();

                t.printStackTrace();

            }
        });

    }

}
