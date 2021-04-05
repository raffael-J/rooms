package com.example.hotelmanagement.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelmanagement.R;
import com.example.hotelmanagement.database.entity.Reservation;
import com.example.hotelmanagement.database.repository.ReservationRepository;

import java.util.Calendar;
import java.util.List;

public class NewReservationActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;

    private EditText mFirstname_editText;
    private EditText mLastname_editText;
    private EditText mRoomname_editText;
    private EditText mCheckin_editText;
    private EditText mCheckout_editText;

    private Button mAdd_btm;
    private Button mBack_btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirstname_editText = (EditText) findViewById(R.id.firstname_editText);
        mLastname_editText = (EditText) findViewById(R.id.lastname_editText);
        mRoomname_editText = (EditText) findViewById(R.id.roomname_editText);
        mCheckin_editText = (EditText) findViewById(R.id.checkin_editText);
        mCheckout_editText = (EditText) findViewById(R.id.checkout_editText);

        mAdd_btm = (Button) findViewById(R.id.add_button_reservations);
        mBack_btm = (Button) findViewById(R.id.back_button_reservationsAdd);

        mCheckin_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(NewReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int day, int month, int year) {
                        mCheckin_editText.setText(day + "-" + month + "-" + year);
                    }

                }, year, month, day);
                datePickerDialog.show();
            }
        });

        mCheckout_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(NewReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int day, int month, int year) {
                        mCheckout_editText.setText(day + "-" + month + "-" + year);
                    }

                }, year, month, day);
                datePickerDialog.show();
            }
        });

        mAdd_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation reservation = new Reservation();
                reservation.setFirstname(mFirstname_editText.getText().toString());
                reservation.setLastname(mLastname_editText.getText().toString());
                reservation.setRoomname(mRoomname_editText.getText().toString());
                reservation.setCheckIn(mCheckin_editText.getText().toString());
                reservation.setCheckOut(mCheckout_editText.getText().toString());
                new ReservationRepository().addReservation(reservation, new ReservationRepository.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Reservation> reservations, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewReservationActivity.this, "The Reservation is inserted succesfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mBack_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });

    }
}