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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;
public class ReservationListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
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
        //return the current authenticated user
        FirebaseUser user = mAuth.getCurrentUser();
        getMenuInflater().inflate(R.menu.reservationlist_activity_menu, menu);
        if (user != null) {
            //means user is authenticated
            menu.getItem(0).setVisible(true); // new reservation
            menu.getItem(1).setVisible(false); // sign in / register
            menu.getItem(2).setVisible(true); // logout
        } else {
            menu.getItem(0).setVisible(false); // new reservation
            menu.getItem(1).setVisible(true); // sign in / register
            menu.getItem(2).setVisible(false); // logout
        }
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            menu.getItem(0).setVisible(true); // new reservation
            menu.getItem(1).setVisible(false); // sign in / register
            menu.getItem(2).setVisible(true); // logout
        } else {
            menu.getItem(0).setVisible(false); // new reservation
            menu.getItem(1).setVisible(true); // sign in / register
            menu.getItem(2).setVisible(false); // logout
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //check the itemid. if the id is "new_reservation" than start activity. create and pass an intent object
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_reservation:
                startActivity(new Intent(this, NewReservationActivity.class));
                return true;
            case R.id.sign_in_reservation:
                startActivity(new Intent(this, SignInActivity.class));
                return true;
            case R.id.sign_out_reservation:
                mAuth.signOut();
                invalidateOptionsMenu(); //reload menu after sign out
                Recycler_Adapter_Reservation.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}