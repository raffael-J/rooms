package com.example.hotelmanagement;


import android.content.Context;
import android.view.LayoutInflater;
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
            for (Room room : rooms) {
                System.out.println(room.getName());
            }
        }

    class RoomItemView extends RecyclerView.ViewHolder {
        private TextView mType;
        private TextView mName;
        private TextView mAmount;

        private String key;

        public RoomItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.room_list_item, parent, false));

            mType = (TextView) itemView.findViewById(R.id.type_textview);
            mName = (TextView) itemView.findViewById(R.id.number_textView);
            mAmount = (TextView) itemView.findViewById(R.id.amount_textView);

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