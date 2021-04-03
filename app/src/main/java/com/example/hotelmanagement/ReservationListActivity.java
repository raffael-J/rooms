package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class ReservationListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewReservations);
        new FirebaseDatabaseHelperReservation().readReservations(new FirebaseDatabaseHelperReservation.DataStatus() {
            @Override
            public void DataIsLoaded(List<Reservation> reservations, List<String> keys) {
                findViewById(R.id.loading_reservations).setVisibility(View.GONE);
                new Recycler_View_Config_Reservation().setConfig(mRecyclerView, ReservationListActivity.this, reservations, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}