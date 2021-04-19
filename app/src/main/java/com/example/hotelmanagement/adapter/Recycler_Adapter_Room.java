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
import com.example.hotelmanagement.ui.RoomDetailsActivity;
import com.example.hotelmanagement.database.entity.Room;
import com.example.hotelmanagement.ui.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Recycler_Adapter_Room {
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    private Context mContext;
    private RoomAdapter mRoomsAdapter;


        //method for the connection between the activity and the layout
        public void setConfig(RecyclerView recyclerView, Context context, List<Room> rooms, List<String> keys) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mContext = context;
            mRoomsAdapter = new RoomAdapter(rooms, keys);
            recyclerView.setAdapter(mRoomsAdapter);

        }

    class RoomItemView extends RecyclerView.ViewHolder {
        private TextView mType;
        private TextView mName;
        private TextView mAmount;
        private String key;

        //this inner class is responsible for the layout implementation. populating with the values
        public RoomItemView(ViewGroup parent) {
            //give it the implemented layout
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.room_list_item, parent, false));

            //initialized the TextViews from the implemented layout
            mName = (TextView) itemView.findViewById(R.id.firstname_textView);
            mType = (TextView) itemView.findViewById(R.id.lastname_textView);
            mAmount = (TextView) itemView.findViewById(R.id.roomname_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user != null) {
                        //pass the objectvalues
                        Intent intent = new Intent(mContext, RoomDetailsActivity.class);
                        intent.putExtra("key", key);
                        intent.putExtra("type", mType.getText().toString());
                        intent.putExtra("name", mName.getText().toString());
                        intent.putExtra("amount", mAmount.getText().toString());

                        mContext.startActivity(intent);
                    } else {
                        mContext.startActivity(new Intent(mContext, SignInActivity.class));
                    }
                }


            });

        }

        //will receive the objectvalues and the keys and populating the textviews
        public void bind(Room room, String key) {
            mType.setText(room.getType());
            mName.setText(room.getName());
            mAmount.setText(room.getAmount());
            this.key = key;
        }

    }
    //this class is responsible for creating itemView and passing object and key to bind method
    class RoomAdapter extends  RecyclerView.Adapter<RoomItemView>{
        private List<Room> mRoomList;
        private List<String> mKeys;

        public RoomAdapter(List<Room> mRoomList, List<String> mKeys) {
            this.mRoomList = mRoomList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RoomItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RoomItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RoomItemView holder, int position) {
            holder.bind(mRoomList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mRoomList.size();
        }
    }
    public static void logout() {user = null;}

}