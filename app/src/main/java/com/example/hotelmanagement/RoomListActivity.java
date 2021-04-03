package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import java.util.List;

public class RoomListActivity extends AppCompatActivity {




    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewRooms);
        new FirebaseDatabaseHelper().readRooms(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Room> rooms, List<String> keys) {
                new Recycler_View_Config().setConfig(mRecyclerView, RoomListActivity.this, rooms, keys);
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


}