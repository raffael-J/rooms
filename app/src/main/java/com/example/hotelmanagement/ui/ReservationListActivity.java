package com.example.hotelmanagement.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hotelmanagement.R;
import com.example.hotelmanagement.adapter.Recycler_Adapter_Reservation;
import com.example.hotelmanagement.database.entity.Reservation;
import com.example.hotelmanagement.database.repository.ReservationRepository;

import java.util.List;

public class ReservationListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialized the TextViews from the implemented layout
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewReservations);
        new ReservationRepository().readReservations(new ReservationRepository.DataStatus() {
            @Override
            public void DataIsLoaded(List<Reservation> reservations, List<String> keys) {
                findViewById(R.id.loading_reservations).setVisibility(View.GONE);
                new Recycler_Adapter_Reservation().setConfig(mRecyclerView, ReservationListActivity.this, reservations, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    // to add a new reservation
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reservationlist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //check the itemid. if the id is "new_room" than start activity. create and pass an intent object
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_reservation:
                startActivity(new Intent(this, NewReservationActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}