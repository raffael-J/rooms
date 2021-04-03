package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class RoomDetailsActivity extends AppCompatActivity {

    private EditText mName_editText;
    private EditText mType_editText;
    private EditText mAmount_editText;

    private Button mUpdate_btm;
    private Button mBack_btm;
    private Button mDelete_btm;

    private String key;
    private String type;
    private String name;
    private String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        key = getIntent().getStringExtra("key");
        type = getIntent().getStringExtra("type");
        name = getIntent().getStringExtra("name");
        amount = getIntent().getStringExtra("amount");

        mName_editText = (EditText) findViewById(R.id.name_editText);
        mName_editText.setText(name);
        mType_editText = (EditText) findViewById(R.id.type_editText);
        mType_editText.setText(type);
        mAmount_editText = (EditText) findViewById(R.id.amount_editText);
        mAmount_editText.setText(amount);

        mUpdate_btm = (Button) findViewById(R.id.update_button);
        mBack_btm = (Button) findViewById(R.id.back_button_addroom);
        mDelete_btm = (Button) findViewById(R.id.delete_button);

        mUpdate_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room room = new Room();
                room.setType(mType_editText.getText().toString());
                room.setName(mName_editText.getText().toString());
                room.setAmount(mAmount_editText.getText().toString());

                new FirebaseDatabaseHelper().updateRoom(key, room, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Room> rooms, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(RoomDetailsActivity.this,"Room is been updated succesfully",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mDelete_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteRoom(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Room> rooms, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(RoomDetailsActivity.this, "Room has been deleted succesfully", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });
        mBack_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });

    }


}