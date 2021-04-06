package com.example.hotelmanagement.database.repository;

import androidx.annotation.NonNull;

import com.example.hotelmanagement.database.entity.Reservation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceReservation;
    private List<Reservation> reservations = new ArrayList<>();

    //different methods that are made avaible for the Reservation Respository
    public interface DataStatus {
        void DataIsLoaded(List<Reservation> reservations, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    //Constructor
    //initialize database object
    public ReservationRepository() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceReservation = mDatabase.getReference("Reservation");
    }

    //everytime you update, delete or insert data, will execute the onDataChange Method

    public void readReservations(final DataStatus dataStatus) {
        mReferenceReservation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clear the list of reservations from old data
                reservations.clear();
                //store the reservation of the reservation node
                List<String> key = new ArrayList<>();
                //this object will contain the key and value of specific node
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    //get the key and save it in the list
                    key.add(keyNode.getKey());
                    //hand over the values
                    Reservation reservation = keyNode.getValue(Reservation.class);
                    reservations.add(reservation);
                }
                dataStatus.DataIsLoaded(reservations,key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addReservation(Reservation reservation, final DataStatus dataStatus) {
        //generate new child node, unique key automatically. save the key in string
        String key = mReferenceReservation.push().getKey();
        mReferenceReservation.child(key).setValue(reservation).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                    dataStatus.DataIsInserted();
            }
        });
    }


    public void updateReservation(String key, Reservation reservation, final DataStatus dataStatus) {
        mReferenceReservation.child(key).setValue(reservation).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteReservation(String key, final DataStatus dataStatus) {
        mReferenceReservation.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }




}
