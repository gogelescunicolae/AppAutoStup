package com.example.stup;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.stup.ui.dashboard.DashboardFragment;
import com.example.stup.ui.home.HomeFragment;
import com.example.stup.ui.notifications.NotificationsFragment;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ButtomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    final FragmentManager fragmentManager=getSupportFragmentManager();

    DashboardFragment dashboardFragment;
    HomeFragment homeFragment;
    NotificationsFragment notificationsFragment;

    private Fragment activeFragment;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_navigation);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

        initializeViews();
        LoadFragment();
    }

    private void initializeViews() {
        dashboardFragment = new DashboardFragment();
        homeFragment = new HomeFragment();
        notificationsFragment = new NotificationsFragment();

        activeFragment = dashboardFragment;
    }

    private void LoadFragment() {
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, notificationsFragment, "1").hide(notificationsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, homeFragment, "2").hide(homeFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, dashboardFragment, "3").hide(dashboardFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager.beginTransaction().hide(activeFragment).show(dashboardFragment).commit();
                activeFragment = dashboardFragment;
                return true;

            case R.id.navigation_dashboard:
                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                activeFragment = homeFragment;
                return true;

            case R.id.navigation_notifications:
                fragmentManager.beginTransaction().hide(activeFragment).show(notificationsFragment).commit();
                activeFragment = notificationsFragment;
                return true;
        }

        return false;
    }
}