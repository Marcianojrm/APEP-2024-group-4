package edu.birzeit.saeedmosaffer.taskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Map;

public class RoomUpdate extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewDetails;
    private String roomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_mainactivity);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDetails = findViewById(R.id.textViewDetails);

        Button btnUpdateTask = findViewById(R.id.btnUpdateTask);
        Button btnDeleteTask = findViewById(R.id.btnDeleteTask);

        Room selectedTask = (Room) getIntent().getSerializableExtra("roomTask");
        roomKey = getIntent().getStringExtra("roomKey");

        if (selectedTask != null) {
            textViewTitle.setText(selectedTask.getTitle());
            textViewDetails.setText(selectedTask.getDetails());
        }

        btnUpdateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomUpdate.this, UpdateRoomActivity.class);
                intent.putExtra("roomTask", (Serializable) selectedTask);
                intent.putExtra("roomKey", roomKey);
                startActivity(intent);
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(selectedTask);
                finish();
            }
        });
    }

    private void deleteTask(Room task) {
        SharedPreferences preferences = getSharedPreferences("RoomData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith("room_")) {
                String json = entry.getValue().toString();
                Room existingTask = Room.fromJson(json);

                if (existingTask.getTitle().equals(task.getTitle())) {
                    editor.remove(entry.getKey());
                    editor.apply();
                    break;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
