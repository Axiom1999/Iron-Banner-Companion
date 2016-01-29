package me.axiom.aapp.ironbannercompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import me.axiom.aapp.ironbannercompanion.api.User;
import me.axiom.aapp.ironbannercompanion.fragments.BountyFragment;
import me.axiom.aapp.ironbannercompanion.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private FloatingActionMenu fab;
    private FloatingActionButton fab_User, fab_Char;

    private User user;
    public String username, membershipId, characterId;
    public int platformId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabMenu);
        tabLayout.setupWithViewPager(viewPager);

        fab = (FloatingActionMenu) findViewById(R.id.fab);
        fab_User = (FloatingActionButton) findViewById(R.id.fab_user);
        fab_Char = (FloatingActionButton) findViewById(R.id.fab_char);

        fab.setOnMenuToggleListener(fabMenuToggleListener);
        fab_User.setOnClickListener(fabClickListener);
        fab_Char.setOnClickListener(fabClickListener);

        if (validateIntentExtras()) {

            username = getIntent().getExtras().getString("username");
            membershipId = getIntent().getExtras().getString("membershipId");
            characterId = getIntent().getExtras().getString("characterId");
            platformId = getIntent().getExtras().getInt("platformId");

        } else {

            Toast.makeText(MainActivity.this, "UNABLE TO LOAD CHARACTER!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginPlatformActivity.class);
            startActivity(intent);
            finish();

        }

    }

    public boolean validateIntentExtras() {

        String u = getIntent().getExtras().getString("username");
        String m = getIntent().getExtras().getString("membershipId");
        String c = getIntent().getExtras().getString("characterId");
        int p = getIntent().getExtras().getInt("platformId");

        if (u == null || m == null || c == null || p == 0) {

            return false;

        } else {

            return true;

        }

    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "HOME");
        adapter.addFragment(new BountyFragment(), "BOUNTIES");
        viewPager.setAdapter(adapter);

    }

    private FloatingActionMenu.OnMenuToggleListener fabMenuToggleListener = new FloatingActionMenu.OnMenuToggleListener() {
        @Override
        public void onMenuToggle(boolean opened) {

            if (opened) {

                fab.getMenuIconView().setImageResource(R.drawable.ic_add);

            } else {

                fab.getMenuIconView().setImageResource(R.drawable.ic_edit);

            }

        }
    };

    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.fab_user:
                    Intent intentUser = new Intent(getBaseContext(), LoginPlatformActivity.class);
                    startActivity(intentUser);
                    finish();
                    break;

                case R.id.fab_char:
                    Intent intentChar = new Intent(getBaseContext(), CharacterSelection.class);
                    startActivity(intentChar);
                    finish();
                    break;

            }

        }
    };

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

}
