package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycler_View_Config_Reservation {
    private Context mContext;
    private ReservationAdapter mReservationAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Reservation> reservations, List<String> keys) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mContext = context;
        mReservationAdapter = new ReservationAdapter(reservations, keys);
        recyclerView.setAdapter(mReservationAdapter);
    }

    class ReservationItemView extends RecyclerView.ViewHolder {
        private TextView mFirstname;
        private TextView mLastname;
        private TextView mRoomid;

        private String key;

        public ReservationItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.reservation_list_item,parent, false));

            mFirstname = (TextView) itemView.findViewById(R.id.firstname_textView);
            mLastname = (TextView) itemView.findViewById(R.id.lastname_textView);
            mRoomid = (TextView) itemView.findViewById(R.id.roomid_textView);



            }

            public void bind(Reservation reservation, String key) {
                mFirstname.setText(reservation.getFirstname());
                mLastname.setText(reservation.getLastname());
                mRoomid.setText(reservation.getRoomid());
                this.key = key;

        }
    }

    class ReservationAdapter extends RecyclerView.Adapter<ReservationItemView> {
        private List<Reservation> mReservationList;
        private List<String> mKeys;

        public ReservationAdapter(List<Reservation> mReservationList, List<String> mKeys) {
            this.mReservationList = mReservationList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ReservationItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReservationItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReservationItemView holder, int position) {
            holder.bind(mReservationList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mReservationList.size();
        }
    }

}
