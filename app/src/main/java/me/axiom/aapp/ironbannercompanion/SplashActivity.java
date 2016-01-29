package me.axiom.aapp.ironbannercompanion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent intentPlatform = new Intent(this, LoginPlatformActivity.class);
        final Intent intentCharacter = new Intent(this, CharacterSelection.class);

        prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        Handler handlerTimer = new Handler();
        handlerTimer.postDelayed(new Runnable() {
            public void run() {

                if (prefs.getString("username", "").equalsIgnoreCase("")) {

                    startActivity(intentPlatform);
                    finish();

                } else {

                    startActivity(intentCharacter);
                    finish();

                }

            }
        }, 3000);

    }
}
