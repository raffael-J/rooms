package com.example.hotelmanagement.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelmanagement.R;
import com.example.hotelmanagement.database.entity.Room;
import com.example.hotelmanagement.database.repository.RoomRepository;

import java.util.List;

public class NewRoomActivity extends AppCompatActivity {

    private EditText mName_editText;
    private EditText mType_editText;
    private EditText mAmount_editText;
    private Button mAdd_btm;
    private Button mBack_btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialized the Edittext
        mName_editText = (EditText) findViewById(R.id.name_editText);
        mType_editText = (EditText) findViewById(R.id.type_editText);
        mAmount_editText = (EditText) findViewById(R.id.amount_editText);

        mAdd_btm = (Button) findViewById(R.id.update_button);
        mBack_btm = (Button) findViewById(R.id.back_button_addroom);

        mAdd_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room room = new Room();
                //populate from the viewobjects
                room.setName(mName_editText.getText().toString());
                room.setType(mType_editText.getText().toString());
                room.setAmount(mAmount_editText.getText().toString());

                //instantiate the RoomRespository
                new RoomRepository().addRoom(room, new RoomRepository.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Room> rooms, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        // toastmessage to inform the user about the successfully insert
                        Toast.makeText(NewRoomActivity.this, "The room is inserted successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        //set onclicklistener on backbutton
        mBack_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });
    }
}