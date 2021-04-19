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
import com.example.hotelmanagement.adapter.Recycler_Adapter_Room;
import com.example.hotelmanagement.database.entity.Room;
import com.example.hotelmanagement.database.repository.RoomRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class RoomListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        //initialized the TextViews from the implemented layout
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewRooms);
        new RoomRepository().readRooms(new RoomRepository.DataStatus() {
            @Override
            public void DataIsLoaded(List<Room> rooms, List<String> keys) {
                findViewById(R.id.loading_rooms).setVisibility(View.GONE);
                new Recycler_Adapter_Room().setConfig(mRecyclerView, RoomListActivity.this, rooms, keys);
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

    // to add a new room
    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseUser user = mAuth.getCurrentUser();
        getMenuInflater().inflate(R.menu.roomlist_activity_menu, menu);
        if (user != null) {
            menu.getItem(0).setVisible(true); // new room
            menu.getItem(1).setVisible(false); // sign in / register
            menu.getItem(2).setVisible(true); // logout
        } else {
            menu.getItem(0).setVisible(false); // new room
            menu.getItem(1).setVisible(true); // sign in / register
            menu.getItem(2).setVisible(false); // logout
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            menu.getItem(0).setVisible(true); // new room
            menu.getItem(1).setVisible(false); // sign in / register
            menu.getItem(2).setVisible(true); // logout
        } else {
            menu.getItem(0).setVisible(false); // new room
            menu.getItem(1).setVisible(true); // sign in / register
            menu.getItem(2).setVisible(false); // logout
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //check the itemid. if the id is "new_room" than start activity. create and pass an intent object
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.new_room:
            startActivity(new Intent(this, NewRoomActivity.class));
            return true;
        case R.id.sign_in_room:
            startActivity(new Intent(this, SignInActivity.class));
            return true;
        case R.id.sign_out_room:
            mAuth.signOut();
            invalidateOptionsMenu();
            Recycler_Adapter_Room.logout();
            return true;
    }
    return super.onOptionsItemSelected(item);
}


}