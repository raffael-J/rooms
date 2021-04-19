package com.example.hotelmanagement.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hotelmanagement.R;
import com.example.hotelmanagement.ui.ReservationDetailsActivity;
import com.example.hotelmanagement.database.entity.Reservation;
import com.example.hotelmanagement.ui.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;
public class Recycler_Adapter_Reservation {
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    private Context mContext;
    private ReservationAdapter mReservationAdapter;
    //method for the connection between the activity and the layout
    public void setConfig(RecyclerView recyclerView, Context context, List<Reservation> reservations, List<String> keys) {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mContext = context;
        mReservationAdapter = new ReservationAdapter(reservations, keys);
        recyclerView.setAdapter(mReservationAdapter);
    }
    class ReservationItemView extends RecyclerView.ViewHolder {
        private TextView mFirstname;
        private TextView mLastname;
        private TextView mRoomname;
        private TextView mCheckin;
        private TextView mCheckout;
        private String key;

        //this inner class is responsible for the layout implementation. populating with the values
        public ReservationItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.reservation_list_item,parent, false));
            //initialized the TextViews from the implemented layout
            mFirstname = (TextView) itemView.findViewById(R.id.firstname_textView);
            mLastname = (TextView) itemView.findViewById(R.id.lastname_textView);
            mRoomname = (TextView) itemView.findViewById(R.id.roomname_textView);
            mCheckin = (TextView) itemView.findViewById(R.id.checkin_textView);
            mCheckout = (TextView) itemView.findViewById(R.id.checkout_textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user != null) {
                        //pass the objectvalues
                        Intent intent = new Intent(mContext, ReservationDetailsActivity.class);
                        intent.putExtra("key",key);
                        intent.putExtra("firstname",mFirstname.getText().toString());
                        intent.putExtra("lastname", mLastname.getText().toString());
                        intent.putExtra("roomname", mRoomname.getText().toString());
                        intent.putExtra("checkIn", mCheckin.getText().toString());
                        intent.putExtra("checkOut", mCheckout.getText().toString());
                        mContext.startActivity(intent);
                    } else {
                        mContext.startActivity(new Intent(mContext, SignInActivity.class));
                    }


                }
            });
        }
        //will receive the objectvalues and the keys and populating the textviews
        public void bind(Reservation reservation, String key) {
            mFirstname.setText(reservation.getFirstname());
            mLastname.setText(reservation.getLastname());
            mRoomname.setText(reservation.getRoomname());
            mCheckin.setText(reservation.getCheckIn());
            mCheckout.setText(reservation.getCheckOut());
            this.key = key;
        }
    }
    //this class is responsible for creating itemView and passing object and key to bind method
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
    public static void logout () {
        user = null;
    }
}