package com.example.hotelmanagement.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.hotelmanagement.R;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchSettings;
    private boolean modeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Toolbar with the arrow with the back function
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // with boolean dark mode "on" and "off"
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        modeOn = prefs.getBoolean("dark", false);
        switchSettings = (Switch) findViewById(R.id.darkmode);
        switchSettings.setChecked(modeOn);
        switchSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            //isChecked true -> darkmode, isChecked false -> darkmode is off
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    modeOn = prefs.edit().putBoolean("dark", true).commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    modeOn = prefs.edit().putBoolean("dark", false).commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

    }
}