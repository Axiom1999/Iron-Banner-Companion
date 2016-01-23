package me.axiom.aapp.ironbannercompanion;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity {

    private String username;
    private int platformId;
    private int characterId;
    private boolean usernameExists;

    private EditText editText_Username;
    private GifImageView gifImageView_Loader_Username;
    private ImageView imageView_Cross_Username, imageView_Tick_Username;

    private View.OnClickListener backListener_From_Username, backListener_From_Character, nextListener_Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_platform);

        editText_Username = (EditText)findViewById(R.id.editText_Username);
        gifImageView_Loader_Username = (GifImageView)findViewById(R.id.gifImageView_Loader_Username);
        imageView_Cross_Username = (ImageView)findViewById(R.id.imageView_Cross_Username);
        imageView_Tick_Username = (ImageView)findViewById(R.id.imageView_Tick_Username);

        Button button_PSN = (Button)findViewById(R.id.button_PSN);
        Button button_XBL = (Button)findViewById(R.id.button_XBL);
        ImageView button_Back_Username = (ImageView)findViewById(R.id.imageView_Go_Back);
        ImageView button_Back_Character = (ImageView)findViewById(R.id.imageView_Back_Character);
        ImageView button_Next_Username = (ImageView)findViewById(R.id.imageView_Next_Username);
        TextView textView_Back_Character = (TextView)findViewById(R.id.textView_Back_Character);
        TextView textView_Next_Username = (TextView)findViewById(R.id.textView_Next_Username);
        TextView textView_Back_Username = (TextView)findViewById(R.id.textView_Back_Username);

        nextListener_Username = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usernameValidate()) {
                    username = editText_Username.getText().toString();

                    boolean isFirstXml = false;
                    LayoutInflater inflator = getLayoutInflater();
                    View view = inflator.inflate(isFirstXml ? R.layout.activity_login_username : R.layout.activity_login_character, null, false);
                    view.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.slide_out_right));
                    setContentView(view);
                } else {
                    editText_Username.setError("Please enter a Username.");
                }
            }
        };

        backListener_From_Username = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFirstXml = false;
                LayoutInflater inflator = getLayoutInflater();
                View view = inflator.inflate(isFirstXml?R.layout.activity_login_username:R.layout.activity_login_platform, null, false);
                view.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.slide_out_right));
                setContentView(view);
            }
        };

        backListener_From_Character = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFirstXml = false;
                LayoutInflater inflator = getLayoutInflater();
                View view = inflator.inflate(isFirstXml?R.layout.activity_login_character:R.layout.activity_login_username, null, false);
                view.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.slide_out_right));
                setContentView(view);
            }
        };

        button_PSN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                platformId = 2;

                boolean isFirstXml = true;
                LayoutInflater inflator = getLayoutInflater();
                View view = inflator.inflate(isFirstXml?R.layout.activity_login_platform:R.layout.activity_login_username, null, false);
                view.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.slide_out_right));
                setContentView(view);
            }
        });

        button_XBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                platformId = 1;

                boolean isFirstXml = true;
                LayoutInflater inflator = getLayoutInflater();
                View view = inflator.inflate(isFirstXml?R.layout.activity_login_platform:R.layout.activity_login_username, null, false);
                view.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.slide_out_right));
                setContentView(view);
            }
        });

        button_Next_Username.setOnClickListener(nextListener_Username);
        textView_Next_Username.setOnClickListener(nextListener_Username);

        button_Back_Username.setOnClickListener(backListener_From_Username);
        textView_Back_Username.setOnClickListener(backListener_From_Username);
        button_Back_Character.setOnClickListener(backListener_From_Character);
        textView_Back_Character.setOnClickListener(backListener_From_Character);

        editText_Username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                gifImageView_Loader_Username.setVisibility(View.VISIBLE);
                imageView_Tick_Username.setVisibility(View.INVISIBLE);
                imageView_Cross_Username.setVisibility(View.INVISIBLE);

                // TODO: Username verification.

            }

            @Override
            public void afterTextChanged(Editable s) {
                gifImageView_Loader_Username.setVisibility(View.INVISIBLE);
                imageView_Tick_Username.setVisibility(View.VISIBLE);
                imageView_Cross_Username.setVisibility(View.INVISIBLE);
            }
        });

    }

    public boolean usernameValidate() {
        boolean valid = true;

        if (editText_Username.getText().toString().isEmpty()) {
            valid = false;
        } else if (usernameExists == false) {
            valid = false;
        }

        return valid;
    }

}
