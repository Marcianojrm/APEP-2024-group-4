package edu.birzeit.saeedmosaffer.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MyRoomMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_room_main);
    }

    public void onAddTaskClick(View view) {
        Intent intent = new Intent(this, AddRoomActivity.class);
        startActivity(intent);
    }

    public void onViewAllClick(View view) {
        Intent intent = new Intent(this, RoomList.class);
        startActivity(intent);
    }
}
