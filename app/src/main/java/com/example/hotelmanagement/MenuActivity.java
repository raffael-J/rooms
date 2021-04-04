package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;


    //Design
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        button1 = (Button) findViewById(R.id.RoomButton);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.ReservationButton);
        button2.setOnClickListener(this);



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

        }
    }
}