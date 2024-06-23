package edu.birzeit.saeedmosaffer.taskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class RoomList extends AppCompatActivity {
    private ArrayList<String> roomList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_dashboard);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        roomList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roomList);

        ListView roomListView = findViewById(R.id.tasks_list);
        roomListView.setAdapter(adapter);

        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = getRoomKey(position);
                Room selectedRoom = getRoom(position);
                Intent intent = new Intent(RoomList.this, RoomUpdate.class);
                intent.putExtra("roomTask", (Serializable) selectedRoom);
                intent.putExtra("roomKey", key);
                startActivity(intent);
            }
        });

        loadRooms();
    }

    private void loadRooms() {
        SharedPreferences preferences = getSharedPreferences("RoomData", MODE_PRIVATE);
        roomList.clear();

        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith("room_")) {
                String json = entry.getValue().toString();
                Room room = Room.fromJson(json);
                roomList.add(room.getTitle() + ": " + room.getDetails());
            }
        }

        adapter.notifyDataSetChanged();
    }

    public void onAddRoomClick(View view) {
        Intent intent = new Intent(this, AddRoomActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadRooms();
        }
    }

    private Room getRoom(int position) {
        SharedPreferences preferences = getSharedPreferences("RoomData", MODE_PRIVATE);
        Map<String, ?> allEntries = preferences.getAll();

        int index = 0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith("room_")) {
                if (index == position) {
                    String json = entry.getValue().toString();
                    return Room.fromJson(json);
                }
                index++;
            }
        }

        return null;
    }

    private String getRoomKey(int position) {
        SharedPreferences preferences = getSharedPreferences("RoomData", MODE_PRIVATE);
        Map<String, ?> allEntries = preferences.getAll();

        int index = 0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith("room_")) {
                if (index == position) {
                    return entry.getKey();
                }
                index++;
            }
        }

        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRooms();
    }

    public void onBackToMainClick(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}
