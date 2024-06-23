package edu.birzeit.saeedmosaffer.taskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class AddRoomActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDetails;
    private Button btnSaveRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_room);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDetails = findViewById(R.id.editTextDetails);
        btnSaveRoom = findViewById(R.id.btnUpdateTask);

        btnSaveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRoom();
            }
        });
    }

    private void saveRoom() {
        String title = editTextTitle.getText().toString();
        String details = editTextDetails.getText().toString();

        if (title.isEmpty() || details.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Room newRoom = new Room(title, details);
        SharedPreferences preferences = getSharedPreferences("RoomData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Generate a unique key for the new room
        String uniqueKey = "room_" + UUID.randomUUID().toString();
        editor.putString(uniqueKey, newRoom.toJson());
        editor.apply();

        Intent intent = new Intent(AddRoomActivity.this, RoomList.class);
        startActivity(intent);
        finish();
    }
}
