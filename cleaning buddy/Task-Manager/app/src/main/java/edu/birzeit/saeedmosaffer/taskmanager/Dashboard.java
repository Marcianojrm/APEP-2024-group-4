package edu.birzeit.saeedmosaffer.taskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    private TextView tvWelcome;
    AppCompatButton taskbtn, logoutbtn, roombtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        logoutbtn = findViewById(R.id.logoutbutton);
        taskbtn = findViewById(R.id.taskbutton);
        tvWelcome = findViewById(R.id.tvWelcome);
        roombtn = findViewById(R.id.roombutton);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        tvWelcome.setText("Welcome, " + username + "!");
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Task 1: Room1", "Status: open\nnot checked off"));
        roomList.add(new Room("Task 2: Room2", "Status: open\nnot checked off"));
        roomList.add(new Room("Task 3: Room3", "Status: open\nnot checked off"));
        roomList.add(new Room("Task 4: Room4", "Status: open\nnot checked off"));
// Create an instance of the adapter
        CardviewAdapter adapter = new CardviewAdapter(roomList);

// Set the adapter to your RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CardviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Room room) {
                // Open the activity with room details
                Intent intent = new Intent(Dashboard.this, LastActivity.class);
//                intent.putExtra("title", room.getTitle());
//                intent.putExtra("details", room.getDetails());
                startActivity(intent);
            }
        });
        roombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Dashboard.this, MyRoomMain.class);
                startActivity(intent2);
            }
        });
        taskbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        // Clear shared preferences (or any other session management logic)
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirect to login activity
        Intent intent = new Intent(Dashboard.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}