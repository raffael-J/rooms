package com.example.hotelmanagement.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotelmanagement.R;
import com.example.hotelmanagement.ui.ReservationListActivity;
import com.example.hotelmanagement.ui.RoomListActivity;
import com.example.hotelmanagement.ui.SettingsActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private TextView more;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("dark", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        button1 = (Button) findViewById(R.id.RoomButton);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.ReservationButton);
        button2.setOnClickListener(this);

        more = (TextView) findViewById(R.id.more);
        more.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RoomButton:
                startActivity(new Intent(this, RoomListActivity.class));
                break;
            case R.id.ReservationButton:
                startActivity(new Intent(this, ReservationListActivity.class));
                break;
            case R.id.more:
                startActivity(new Intent(this, SettingsActivity.class));

        }
    }
}