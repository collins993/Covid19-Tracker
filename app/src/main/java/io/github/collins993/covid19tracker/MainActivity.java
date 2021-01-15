package io.github.collins993.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String HOME_FRAGMENT = "home_fragment";
    public static final String STATS_FRAGMENT = "status_fragment";
    public static final String HOME = "Home";
    public static final String STATS = "Covid-19 Status";

    private TextView titleTxt;
    private ImageView refreshBtn;
    private BottomNavigationView navigationView;

    //fragments
    private Fragment homeFragment, statsFragment;
    private Fragment activeFragment;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTxt =findViewById(R.id.title_txt);
        refreshBtn =findViewById(R.id.refresh_btn);
        navigationView =findViewById(R.id.navigation_view);

        initFragments();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeFragment.onResume();
                statsFragment.onResume();
            }
        });

        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        statsFragment = new StatsFragment();

        fragmentManager = getSupportFragmentManager();
        activeFragment = homeFragment;
        fragmentManager.beginTransaction().add(R.id.frame, homeFragment, HOME_FRAGMENT).commit();
        fragmentManager.beginTransaction().add(R.id.frame, statsFragment, STATS_FRAGMENT).hide(statsFragment).commit();

    }

    private void loadHomeFragment() {
        titleTxt.setText(HOME);
        fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
        activeFragment = homeFragment;
    }

    private void loadStatsFragment() {
        titleTxt.setText(STATS);
        fragmentManager.beginTransaction().hide(activeFragment).show(statsFragment).commit();
        activeFragment = statsFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //handle bottom nav clicks
        switch (item.getItemId()){
            case R.id.nav_home:
                loadHomeFragment();
                return true;
            case R.id.nav_stats:
                loadStatsFragment();
                return true;
        }
        return false;
    }
}