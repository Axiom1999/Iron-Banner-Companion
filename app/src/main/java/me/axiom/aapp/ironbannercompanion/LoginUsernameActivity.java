package me.axiom.aapp.ironbannercompanion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import me.axiom.aapp.ironbannercompanion.api.DestinyAPI;
import me.axiom.aapp.ironbannercompanion.api.User;
import me.axiom.aapp.ironbannercompanion.api.responses.MembershipIdResponse;
import pl.droidsonroids.gif.GifImageView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginUsernameActivity extends AppCompatActivity {

    private Intent intent_Character, intent_Platform;
    private int platformId;
    private User user;
    private String membershipId;

    private ProgressDialog progressDialog;

    private DestinyAPI destinyAPI;

    private Button button_Back, button_Next;
    private EditText editText_Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_username);

        DestinyAPI.initServer(this, "167b5904935a43cc9b188475002e7b1f");
        destinyAPI = DestinyAPI.getInstance();

        platformId = getIntent().getExtras().getInt("platformId");

        button_Back = (Button) findViewById(R.id.button_Back_Username);
        button_Next = (Button) findViewById(R.id.button_Next_Username);

        editText_Username = (EditText) findViewById(R.id.editText_Username);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText_Username, InputMethodManager.SHOW_IMPLICIT);

        intent_Platform = new Intent(this, LoginPlatformActivity.class);
        intent_Character = new Intent(this, LoginCharacterActivity.class);

        button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the Platform selection view.
                startActivity(intent_Platform);
            }
        });

        button_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {

                    progressDialog = new ProgressDialog(LoginUsernameActivity.this,
                            R.style.AppTheme_Custom_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("VALIDATING USER...");
                    progressDialog.show();

                    validateUser();

                }

            }
        });

    }

    public void validateUser() {

        user = new User();
        user.setGamerTag(editText_Username.getText().toString());
        user.setPlatformId(platformId);

        destinyAPI.getMembershipId(user, new Callback<MembershipIdResponse>() {

            @Override
            public void onResponse(Response<MembershipIdResponse> response, Retrofit retrofit) {

                String id = response.body().getResponse();

                Log.d("API_CALL", "MembershipId Response! " + "(ID = " + id + ")");

                if (!id.equalsIgnoreCase("0")) {

                    membershipId = id;
                    onUserSuccess();

                } else {

                    onUserFailure(false);

                }
            }

            @Override
            public void onFailure(Throwable t) {

                onUserFailure(true);
                Log.d("API_CALL", "MembershipId Failure.");
                t.printStackTrace();

            }
        });
    }

    public void onUserSuccess() {
        intent_Character.putExtra("platformId", platformId);
        intent_Character.putExtra("username", editText_Username.getText().toString());
        intent_Character.putExtra("membershipId", membershipId);
        progressDialog.dismiss();
        startActivity(intent_Character);
        finish();
    }

    public void onUserFailure(boolean b) {
        if(b) {
            progressDialog.dismiss();
            Toast.makeText(getBaseContext(), "ERROR: Connection failure.", Toast.LENGTH_SHORT).show();
            Log.d("AUTHENTICATION", "Login_FAILED (" + editText_Username.getText().toString() + " on " + platformId + ")");
            button_Next.setEnabled(true);
        } else {
            progressDialog.dismiss();
            editText_Username.setError("Username not found on the selected platform.");
            Log.d("AUTHENTICATION", "Login_FAILED (" + editText_Username.getText().toString() + " on " + platformId + ")");
            button_Next.setEnabled(true);
        }
    }

    public boolean validate() {

        String username = editText_Username.getText().toString();
        int platform = platformId;

        if (username.isEmpty()) {
            editText_Username.setError("You must enter a username.");
            return false;
        }

        if (platform == 0) {
            Toast.makeText(getBaseContext(), "ERROR: Platform is NULL.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

}
