package com.example.hotelmanagement;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperReservation {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceReservation;
    private List<Reservation> reservations = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Reservation> reservations, List<String> keys);
        void DataIsInserted();
        void DataIsDeleted();
    }


    public FirebaseDatabaseHelperReservation() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceReservation = mDatabase.getReference("Reservation");
    }

    public void readReservations(final DataStatus dataStatus) {
        mReferenceReservation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reservations.clear();
                List<String> key = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    key.add(keyNode.getKey());
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




}
