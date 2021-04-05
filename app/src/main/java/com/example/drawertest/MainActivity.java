package com.example.drawertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.drawertest.fragments.HomeFragment;
import com.example.drawertest.fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private boolean state = true;
    private NavigationView navigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();
    private Fragment fragment = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        sendSettingsToFragment();
        switch (item.getItemId()){
            case R.id.nav_home:
                if (fragment != homeFragment) {
                    fragment = homeFragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    navigationView.setCheckedItem(R.id.nav_home);
                }
                break;
            case R.id.nav_settings:
                if (fragment != settingsFragment) {
                    fragment = settingsFragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    navigationView.setCheckedItem(R.id.nav_settings);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return false;
    }
    private void sendSettingsToFragment(){
        try{
            state = settingsFragment.getSwitchState();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println(state);
        Bundle bundle = new Bundle();
        bundle.putBoolean("Switch",state);
        homeFragment.setArguments(bundle);
    }
}