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

public class Recycler_View_Config {
    private Context mContext;
    private RoomAdapter mRoomsAdapter;

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

        public RoomItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.room_list_item, parent, false));

            mName = (TextView) itemView.findViewById(R.id.firstname_textView);
            mType = (TextView) itemView.findViewById(R.id.lastname_textView);
            mAmount = (TextView) itemView.findViewById(R.id.roomname_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RoomDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("type",mType.getText().toString());
                    intent.putExtra("name",mName.getText().toString());
                    intent.putExtra("amount",mAmount.getText().toString());

                    mContext.startActivity(intent);
                }


            });

        }

        public void bind(Room room, String key) {
            mType.setText(room.getType());
            mName.setText(room.getName());
            mAmount.setText(room.getAmount());
            this.key = key;
        }

    }

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

}