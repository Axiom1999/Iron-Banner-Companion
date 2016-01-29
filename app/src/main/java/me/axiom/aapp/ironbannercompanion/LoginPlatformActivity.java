package me.axiom.aapp.ironbannercompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginPlatformActivity extends AppCompatActivity {

    private Intent intent_Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_platform);

        Button button_PSN = (Button) findViewById(R.id.button_PSN);
        Button button_XBL = (Button) findViewById(R.id.button_Change_User);

        intent_Username = new Intent(this, LoginUsernameActivity.class);

        button_PSN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to entering the Username while saving their chosen platform (PLAYSTATION).
                intent_Username.putExtra("platformId", 2);
                startActivity(intent_Username);
            }
        });

        button_XBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to entering the Username while saving their chosen platform(XBOX).
                intent_Username.putExtra("platformId", 1);
                startActivity(intent_Username);
            }
        });
    }
}
