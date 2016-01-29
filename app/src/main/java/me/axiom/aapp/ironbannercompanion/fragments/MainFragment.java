package me.axiom.aapp.ironbannercompanion.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import at.grabner.circleprogress.AnimationState;
import at.grabner.circleprogress.AnimationStateChangedListener;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import at.grabner.circleprogress.UnitPosition;
import me.axiom.aapp.ironbannercompanion.MainActivity;
import me.axiom.aapp.ironbannercompanion.R;
import me.axiom.aapp.ironbannercompanion.api.DestinyAPI;
import me.axiom.aapp.ironbannercompanion.api.User;
import me.axiom.aapp.ironbannercompanion.api.data.CharacterProgressions;
import me.axiom.aapp.ironbannercompanion.api.responses.CharacterProgressionResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainFragment extends Fragment {

    private DestinyAPI destinyAPI;
    private User user;
    private String username, membershipId, characterId;
    private int platformId;

    private int ironBanner_Level, ironBanner_maxRep, ironBanner_CurrentRep;

    private CircleProgressView progressView_Rep;
    private TextView textView_Level, textView_Max;

    public MainFragment() {

        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        DestinyAPI.initServer(this.getContext(), "167b5904935a43cc9b188475002e7b1f");
        destinyAPI = DestinyAPI.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        textView_Level = (TextView) view.findViewById(R.id.textView_Level);
        textView_Max = (TextView) view.findViewById(R.id.textView_Max);
        progressView_Rep = (CircleProgressView) view.findViewById(R.id.progressView_Rep);

        username = getActivity().getIntent().getExtras().getString("username");
        membershipId = getActivity().getIntent().getExtras().getString("membershipId");
        characterId = getActivity().getIntent().getExtras().getString("characterId");
        platformId = getActivity().getIntent().getExtras().getInt("platformId");
        createUser();

        progressView_Rep.setBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        progressView_Rep.setShowTextWhileSpinning(true); // Show/hide text in spinning mode
        progressView_Rep.setText("...");
        progressView_Rep.setAutoTextSize(true);
        progressView_Rep.setSpinSpeed(3);
        progressView_Rep.setUnitVisible(false);
        progressView_Rep.setUnitPosition(UnitPosition.RIGHT_TOP);
        progressView_Rep.setTextMode(TextMode.TEXT);

        Handler handlerTimer = new Handler();

        handlerTimer.postDelayed(new Runnable() {
            public void run() {
                // Delay spin, for view created.

                progressView_Rep.spin();

                progressView_Rep.setOnAnimationStateChangedListener(
                        new AnimationStateChangedListener() {
                            @Override
                            public void onAnimationStateChanged(AnimationState _animationState) {
                                switch (_animationState) {
                                    case IDLE:
                                    case ANIMATING:
                                    case START_ANIMATING_AFTER_SPINNING:
                                        progressView_Rep.setTextMode(TextMode.VALUE); // show value if not spinning
                                        progressView_Rep.setUnitVisible(true);
                                        break;
                                    case SPINNING:
                                        progressView_Rep.setTextMode(TextMode.TEXT); // show text while spinning
                                        progressView_Rep.setUnitVisible(false);
                                    case END_SPINNING:
                                        break;
                                    case END_SPINNING_START_ANIMATING:
                                        break;

                                }
                            }
                        }
                );

                getIronBannerInfo();

            }
        }, 1000);

    }

    public void createUser() {

        //Log.d("MAIN_FRAGMENT", "Creating User.");

        user = new User();
        user.setGamerTag(username);
        user.setPlatformId(platformId);
        user.setMembershipId(membershipId);

    }

    public void updateProgressView() {

        //Log.d("MAIN_FRAGMENT", "Updating Progress View!");

        if (ironBanner_Level == 5) {

            progressView_Rep.setBarColor(ContextCompat.getColor(getContext(), R.color.colorAccented), ContextCompat.getColor(getContext(), R.color.colorAccent));
            progressView_Rep.setMaxValue(ironBanner_maxRep);
            progressView_Rep.setUnitVisible(false);
            progressView_Rep.setText("");
            progressView_Rep.setTextMode(TextMode.TEXT);
            progressView_Rep.setTextSize(0);
            textView_Level.setText("Rank " + String.valueOf(ironBanner_Level));
            textView_Max.setVisibility(View.VISIBLE);

        } else {

            progressView_Rep.stopSpinning();
            progressView_Rep.setMaxValue(ironBanner_maxRep);
            progressView_Rep.setValueAnimated(ironBanner_CurrentRep);
            progressView_Rep.setUnit("/" + ironBanner_maxRep);
            progressView_Rep.setUnitVisible(true);
            progressView_Rep.setTextMode(TextMode.VALUE);
            textView_Level.setText("Rank " + String.valueOf(ironBanner_Level));

        }

    }

    public void getIronBannerInfo() {

        //Log.d("MAIN_FRAGMENT", "Attempting to retrieve Iron Banner progression.");

        destinyAPI.getCharacterProgression(user, characterId, new Callback<CharacterProgressionResponse>() {
            @Override
            public void onResponse(Response<CharacterProgressionResponse> response, Retrofit retrofit) {

                Log.d("MAIN_FRAGMENT", "CharacterProgression Response!");

                CharacterProgressions[] characterProgressions = response.body().getResponse().getData().getProgressions();

                for (int i = 0 ; i < characterProgressions.length ; i++) {

                    //Log.d("MAIN_FRAGMENT", "Progression Hash " + "(" + i + ")" + ": " + characterProgressions[i].getProgressionHash());

                    if (characterProgressions[i].getProgressionHash() == 2161005788L) {

                        Log.d("MAIN_FRAGMENT", "Found correct Progression Hash for Iron Banner!");

                        ironBanner_Level = characterProgressions[i].getLevel();
                        ironBanner_maxRep = characterProgressions[i].getNextLevelAt();
                        ironBanner_CurrentRep = characterProgressions[i].getProgressToNextLevel();

                        updateProgressView();

                    }

                }

            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("MAIN_FRAGMENT", "Unable to retrieve Iron Banner information.");
                t.printStackTrace();
                //Log.d("MAIN_FRAGMENT", "Unable to retrieve Iron Banner information.");

            }
        });

    }

}
