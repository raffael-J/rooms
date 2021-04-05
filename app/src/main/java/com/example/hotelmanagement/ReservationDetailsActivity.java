package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.SignInButtonImpl;

import java.util.Calendar;
import java.util.List;

public class ReservationDetailsActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private EditText mFirstname_edittext;
    private EditText mLastname_edittext;
    private EditText mRoomname_edittext;
    private EditText mCheckin_edittext;
    private EditText mCheckout_edittext;

    private Button mBack_btm;
    private Button mDelete_btm;
    private Button mUpdate_btn;


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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        key = getIntent().getStringExtra("key");
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
        roomname = getIntent().getStringExtra("roomname");
        checkIn = getIntent().getStringExtra("checkIn");
        checkOut = getIntent().getStringExtra("checkOut");

        mFirstname_edittext = (EditText) findViewById(R.id.firstname_details_edittext);
        mFirstname_edittext.setText(firstname);
        mLastname_edittext = (EditText) findViewById(R.id.name_details_edittext);
        mLastname_edittext.setText(lastname);
        mRoomname_edittext = (EditText) findViewById(R.id.roomname_details_edittext);
        mRoomname_edittext.setText(roomname);
        mCheckin_edittext = (EditText) findViewById(R.id.checkin_details_edittext);
        mCheckin_edittext.setText(checkIn);
        mCheckout_edittext = (EditText) findViewById(R.id.checkout_details_edittext);
        mCheckout_edittext.setText(checkOut);

        mBack_btm = (Button) findViewById((R.id.back_button_detailsReservation));
        mDelete_btm = (Button) findViewById((R.id.delete_button_detailsReservation));
        mUpdate_btn = (Button)  findViewById(R.id.update_button_detailsReservation);

        mCheckin_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(ReservationDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int day, int month, int year) {
                        mCheckin_edittext.setText(day + "-" + month + "-" + year);
                    }

                }, year, month, day);
                datePickerDialog.show();
            }
        });

        mCheckout_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(ReservationDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int day, int month, int year) {
                            mCheckout_edittext.setText(day + "-" + month + "-" + year);
                        }

                    }, year, month, day);
                    datePickerDialog.show();
            }
        });






        mBack_btm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });


        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation reservation = new Reservation();
                reservation.setFirstname(mFirstname_edittext.getText().toString());
                reservation.setLastname(mLastname_edittext.getText().toString());
                reservation.setRoomname(mRoomname_edittext.getText().toString());
                reservation.setCheckIn(mCheckin_edittext.getText().toString());
                reservation.setCheckOut(mCheckout_edittext.getText().toString());

                new FirebaseDatabaseHelperReservation().updateReservation(key, reservation, new FirebaseDatabaseHelperReservation.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Reservation> reservations, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(ReservationDetailsActivity.this,"Reservation is been updated succesfully",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
         }
        });

        mDelete_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelperReservation().deleteReservation(key, new FirebaseDatabaseHelperReservation.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Reservation> reservations, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(ReservationDetailsActivity.this, "Reservation has been deleted succesfully", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });


    }
}