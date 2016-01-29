package me.axiom.aapp.ironbannercompanion.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.liulishuo.magicprogresswidget.MagicProgressBar;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import me.axiom.aapp.ironbannercompanion.DateAndTime;
import me.axiom.aapp.ironbannercompanion.R;
import me.axiom.aapp.ironbannercompanion.api.DestinyAPI;
import me.axiom.aapp.ironbannercompanion.api.User;
import me.axiom.aapp.ironbannercompanion.api.data.CharacterProgressions;
import me.axiom.aapp.ironbannercompanion.api.responses.CharacterProgressionResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class BountyFragment extends Fragment {

    private DestinyAPI destinyAPI;
    private User user;
    private String username, membershipId, characterId;
    private int platformId, ironBanner_Level, ironBanner_Rep, ironBanner_maxRep, ironBanner_totalRep;

    private double[] temperingBuffValue = {1.0, 1.5, 0.0, 0.15, 0.25, 0.4, 0.6};
    private double[] temperingBuffValueTextSPECIAL = {0.0, 0.15, 0.25, 0.4, 0.6, 1.0, 1.5};

    private int[] ranksTotalRep = {0,100,1300,3700,6100,8500};
    private int[] ranksMaxRepPerLevel = {100,1200,2400,2400,2400};
    private double repEarnt = 0;

    private Spinner spinner_RankWanted, spinner_DailyBounties, spinner_WeeklyBounties, spinner_TemperingDay;
    private TextView textView_RepNeeded, textView_RepEarnt, textView_RepPerGame, textView_TemperingBuff, textView_RepPerMedallion;
    private TextView textView_NewRank_Rank;
    private TextView textView_DailyBountyDesc, textView_WeeklyBountyDesc;
    private CheckBox checkBox_Emblem, checkBox_Shader, checkBox_ClassItem, checkBox_AlternateCharacter;
    private MagicProgressBar progressBar_NewRank;

    private DateAndTime dateAndTime;

    private boolean emblem, classItem, shader, altChar;

    public BountyFragment() {

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
        return inflater.inflate(R.layout.fragment_bounty, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaneState) {

        dateAndTime = new DateAndTime();
        dateAndTime.init();

        textView_RepNeeded = (TextView) view.findViewById(R.id.textView_RepNeeded);
        textView_RepEarnt = (TextView) view.findViewById(R.id.textView_RepEarnt_Value);
        textView_RepPerGame = (TextView) view.findViewById(R.id.textView_Per_Game_Value);
        textView_TemperingBuff = (TextView) view.findViewById(R.id.textView_TemperingBuff_Value);
        textView_RepPerMedallion = (TextView) view.findViewById(R.id.textView_Per_Medallion_Value);
        textView_DailyBountyDesc = (TextView) view.findViewById(R.id.textView_Daily_Bounties_Description);
        textView_WeeklyBountyDesc = (TextView) view.findViewById(R.id.textView_Weekly_Bounties_Description);
        textView_NewRank_Rank = (TextView) view.findViewById(R.id.textView_NewRank_Rank);

        textView_TemperingBuff.setText((int) (temperingBuffValue[dateAndTime.getDayOfWeek()] * 100) + "%");

        spinner_RankWanted = (Spinner) view.findViewById(R.id.spinner_Rank);
        spinner_WeeklyBounties = (Spinner) view.findViewById(R.id.spinner_Weekly);
        spinner_DailyBounties = (Spinner) view.findViewById(R.id.spinner_Daily);
        spinner_TemperingDay = (Spinner) view.findViewById(R.id.spinner_TemperingDay);
        spinner_RankWanted.setSelection(0);
        spinner_WeeklyBounties.setSelection(0);
        spinner_DailyBounties.setSelection(0);
        setTemperingDay();
        spinner_RankWanted.setOnItemSelectedListener(onRankSpinnerSelectionListener);
        spinner_WeeklyBounties.setOnItemSelectedListener(onSpinnerSelectionListener);
        spinner_DailyBounties.setOnItemSelectedListener(onSpinnerSelectionListener);
        spinner_TemperingDay.setOnItemSelectedListener(onSpinnerSelectionListener);

        progressBar_NewRank = (MagicProgressBar) view.findViewById(R.id.progressBar_NewRank);

        checkBox_AlternateCharacter = (CheckBox) view.findViewById(R.id.checkBox_AltChar);
        checkBox_Emblem = (CheckBox) view.findViewById(R.id.checkBox_Emblem);
        checkBox_ClassItem = (CheckBox) view.findViewById(R.id.checkBox_ClassItem);
        checkBox_Shader = (CheckBox) view.findViewById(R.id.checkBox_Shader);
        checkBox_AlternateCharacter.setOnCheckedChangeListener(altCharBuffChangeListener);
        checkBox_Emblem.setOnCheckedChangeListener(emblemBuffChangeListener);
        checkBox_ClassItem.setOnCheckedChangeListener(classItemBuffChangeListener);
        checkBox_Shader.setOnCheckedChangeListener(shaderBuffChangeListener);

        username = getActivity().getIntent().getExtras().getString("username");
        membershipId = getActivity().getIntent().getExtras().getString("membershipId");
        characterId = getActivity().getIntent().getExtras().getString("characterId");
        platformId = getActivity().getIntent().getExtras().getInt("platformId");
        createUser();
        getIronBannerInfo();

    }

    public void setTemperingDay() {

        int dayNumber = dateAndTime.getDayOfWeek();

        if (dayNumber == 0) {

            spinner_TemperingDay.setSelection(5);

        } else if (dayNumber == 1) {

            spinner_TemperingDay.setSelection(6);

        } else {

            spinner_TemperingDay.setSelection(dayNumber-2);

        }

    }

    public CheckBox.OnCheckedChangeListener altCharBuffChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                altChar = true;
            } else {
                altChar = false;
            }

            updateOverview();
            updateBountyDesc();

        }
    };

    public CheckBox.OnCheckedChangeListener emblemBuffChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                emblem = true;
            } else {
                emblem = false;
            }

            updateOverview();
            updateBountyDesc();

        }
    };

    public CheckBox.OnCheckedChangeListener shaderBuffChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                shader = true;
            } else {
                shader = false;
            }

            updateOverview();
            updateBountyDesc();

        }
    };

    public CheckBox.OnCheckedChangeListener classItemBuffChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                classItem = true;
            } else {
                classItem = false;
            }

            updateOverview();
            updateBountyDesc();

        }
    };

    public Spinner.OnItemSelectedListener onSpinnerSelectionListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            updateOverview();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public Spinner.OnItemSelectedListener onRankSpinnerSelectionListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            int totalRepNeeded = ranksTotalRep[position + 1];
            textView_RepNeeded.setText(String.valueOf(totalRepNeeded - ironBanner_totalRep));

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void createUser() {

        user = new User();
        user.setGamerTag(username);
        user.setPlatformId(platformId);
        user.setMembershipId(membershipId);

    }

    public void updateBountyDesc() {

        textView_WeeklyBountyDesc.setText((int)getReputation(525, 1) + " reputation each...");
        textView_DailyBountyDesc.setText((int)getReputation(125, 1) + " reputation each...");

    }

    public void updateNewRankProgress() {

        double newTotalRep = ironBanner_totalRep + repEarnt;
        int newRank = 0;
        double newRep = 0;
        int newMaxRep = 0;

        for (int i = 0; i < ranksTotalRep.length - 1; i++) {

            if (newTotalRep <= ranksTotalRep[0]) {
                newRank = 0;
                newRep = 0;
                newMaxRep = 100;

                progressBar_NewRank.setPercent((float)(newRep / newMaxRep));
                textView_NewRank_Rank.setText("RANK " + newRank + "   (" + (int) newRep + "/" + newMaxRep + ")");

            } else {

                if (newTotalRep >= ranksTotalRep[5]) {
                    newRank = 5;
                    newRep = 0;
                    newMaxRep = 0;

                    progressBar_NewRank.setPercent((float)(newRep / newMaxRep));
                    textView_NewRank_Rank.setText("RANK " + newRank + "   (MAX)");

                } else {

                    if (newTotalRep < ranksTotalRep[i + 1] && newTotalRep >= ranksTotalRep[i]) {

                        newRank = i;
                        newRep = newTotalRep - ranksTotalRep[i];
                        newMaxRep = ranksMaxRepPerLevel[i];

                        progressBar_NewRank.setPercent((float) (newRep / newMaxRep));
                        textView_NewRank_Rank.setText("RANK " + newRank + "   (" + (int) newRep + "/" + newMaxRep + ")");

                        //Log.d("BOUNTY_FRAGMENT", "New Percentage: " + ((float)newRep/newMaxRep));
                        //Log.d("BOUNTY_FRAGMENT", "Rank: " + newRank + " | Rep: " + newRep + " | Max: " + newMaxRep);
                    }
                }
            }
        }

    }

    public void updateOverview() {

        double earnt = 0;
        int weeklyBounties = spinner_WeeklyBounties.getSelectedItemPosition();
        int dailyBounties = spinner_DailyBounties.getSelectedItemPosition();

        double repFromWeeklies = getReputation(525, weeklyBounties);
        double repFromDailies = getReputation(125, dailyBounties);

        earnt = repFromDailies + repFromWeeklies;
        repEarnt = earnt;

        updateNewRankProgress();
        updateBountyDesc();

        textView_TemperingBuff.setText((int) (temperingBuffValueTextSPECIAL[spinner_TemperingDay.getSelectedItemPosition()] * 100) + "%");
        textView_RepEarnt.setText(String.valueOf((int)earnt));
        textView_RepPerGame.setText(String.valueOf((int)getReputation(50, 1)));
        textView_RepPerMedallion.setText(String.valueOf((int)getReputation(40, 1)));

    }

    public double getReputation(int rep, int quantity) {

        int newRep = rep;

        double buff = 1 + temperingBuffValueTextSPECIAL[spinner_TemperingDay.getSelectedItemPosition()];

        if (emblem) {
            buff *= 1.1;
        }
        if (shader) {
            buff *= 1.1;
        }
        if (classItem) {
            buff *= 1.1;
        }
        if (altChar) {
            buff *= 2.0;
        }

        return Math.floor(rep*buff*quantity);

    }

    public void getIronBannerInfo() {

        //Log.d("MAIN_FRAGMENT", "Attempting to retrieve Iron Banner progression.");

        destinyAPI.getCharacterProgression(user, characterId, new Callback<CharacterProgressionResponse>() {
            @Override
            public void onResponse(Response<CharacterProgressionResponse> response, Retrofit retrofit) {

                Log.d("BOUNTY_FRAGMENT", "CharacterProgression Response!");

                CharacterProgressions[] characterProgressions = response.body().getResponse().getData().getProgressions();

                for (int i = 0; i < characterProgressions.length; i++) {

                    //Log.d("BOUNTY_FRAGMENT", "Progression Hash " + "(" + i + ")" + ": " + characterProgressions[i].getProgressionHash());

                    if (characterProgressions[i].getProgressionHash() == 2161005788L) {

                        Log.d("BOUNTY_FRAGMENT", "Found correct Progression Hash for Iron Banner!");

                        ironBanner_Level = characterProgressions[i].getLevel();
                        ironBanner_maxRep = characterProgressions[i].getNextLevelAt();
                        ironBanner_Rep = characterProgressions[i].getProgressToNextLevel();
                        ironBanner_totalRep = ranksTotalRep[characterProgressions[i].getLevel()] + characterProgressions[i].getProgressToNextLevel();

                        spinner_RankWanted.setSelection(characterProgressions[i].getLevel());

                        textView_RepNeeded.setText(String.valueOf(ranksTotalRep[characterProgressions[i].getLevel()+1] - ironBanner_totalRep));

                        updateOverview();

                    }

                }

            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("BOUNTY_FRAGMENT", "Unable to retrieve Iron Banner information.");
                t.printStackTrace();

            }
        });

    }

}
