package edu.birzeit.saeedmosaffer.taskmanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateRoomActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDetails;
    private Button btnUpdateRoom;
    private String roomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_room2);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDetails = findViewById(R.id.editTextDetails);
        btnUpdateRoom = findViewById(R.id.btnUpdateTask);

        Room selectedRoom = (Room) getIntent().getSerializableExtra("roomTask");
        roomKey = getIntent().getStringExtra("roomKey");

        displayOldData(selectedRoom);

        btnUpdateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRoom(selectedRoom);
                Intent intent = new Intent(UpdateRoomActivity.this, RoomList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void displayOldData(Room room) {
        if (room != null) {
            editTextTitle.setText(room.getTitle());
            editTextDetails.setText(room.getDetails());
        }
    }

    private void updateRoom(Room room) {
        String newTitle = editTextTitle.getText().toString();
        String newDetails = editTextDetails.getText().toString();
        room.setTitle(newTitle);
        room.setDetails(newDetails);

        SharedPreferences preferences = getSharedPreferences("RoomData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(roomKey, room.toJson());
        editor.apply();
    }
}
