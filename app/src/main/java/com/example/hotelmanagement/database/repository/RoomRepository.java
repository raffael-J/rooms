package com.example.hotelmanagement.database.repository;


import androidx.annotation.NonNull;

import com.example.hotelmanagement.database.entity.Room;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceRooms;
    private List<Room> rooms = new ArrayList<>();

    //different methods that are made avaible for the Reservation Respository
    public interface DataStatus {
        void DataIsLoaded(List<Room> rooms, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    //Constructor
    //initialize database object
    public RoomRepository() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceRooms = mDatabase.getReference("Rooms");
    }


    //everytime you update, delete or insert data, will execute the onDataChange Method
    public void readRooms(final DataStatus dataStatus) {
        mReferenceRooms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clear the list of reservations from old data
                rooms.clear();
                //store the reservation of the reservation node
                List<String> key = new ArrayList<>();
                //this object will contain the key and value of specific node
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    //get the key and save it in the list
                    key.add(keyNode.getKey());
                    //hand over the values
                    Room room = keyNode.getValue(Room.class);
                    rooms.add(room);
                }
                dataStatus.DataIsLoaded(rooms, key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addRoom(Room room, final DataStatus dataStatus) {
        //generate new child node, unique key automatically. save the key in string
        String key = mReferenceRooms.push().getKey();
        mReferenceRooms.child(key).setValue(room).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void updateRoom(String key, Room room, final DataStatus dataStatus) {
        mReferenceRooms.child(key).setValue(room).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteRoom(String key, final DataStatus dataStatus) {
        mReferenceRooms.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }


}
