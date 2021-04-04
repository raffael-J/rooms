package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReservationDetailsActivity extends AppCompatActivity {

    private TextView mFirstname_textView;
    private TextView mLastname_textView;
    private TextView mRoomname_textView;
    private TextView mCheckin_textView;
    private TextView mCheckout_textView;

    private Button mBack_btm;
    private Button mDelete_btm;


    private String key;
    private String firstname;
    private String lastname;
    private String roomname;
    private String checkIn;
    private String checkOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        key = getIntent().getStringExtra("key");
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
        roomname = getIntent().getStringExtra("roomname");
        checkIn = getIntent().getStringExtra("checkIn");
        checkOut = getIntent().getStringExtra("checkOut");

        mFirstname_textView = (TextView) findViewById(R.id.firstname_details_TextView);
        mFirstname_textView.setText(firstname);
        mLastname_textView = (TextView) findViewById(R.id.lastname_details_TextView);
        mLastname_textView.setText(lastname);
        mRoomname_textView = (TextView) findViewById(R.id.roomname_details_TextView);
        mRoomname_textView.setText(roomname);
        mCheckin_textView = (TextView) findViewById(R.id.checkin_details_TextView);
        mCheckin_textView.setText(checkIn);
        mCheckout_textView = (TextView) findViewById(R.id.checkout_details_TextView);
        mCheckout_textView.setText(checkOut);

        mBack_btm = (Button) findViewById((R.id.back_button_detailsReservation));
        mDelete_btm = (Button) findViewById((R.id.delete_button_detailsReservation));

        mBack_btm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });

        mDelete_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelperReservation().
            }
        });


    }
}