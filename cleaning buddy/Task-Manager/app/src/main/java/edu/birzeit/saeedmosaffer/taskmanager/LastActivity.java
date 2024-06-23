package edu.birzeit.saeedmosaffer.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LastActivity extends AppCompatActivity {
    AppCompatButton roombutton, taskbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_last);
        roombutton = findViewById(R.id.buttonr);
        taskbutton = findViewById(R.id.button2t);
        roombutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LastActivity.this, MyRoomMain.class);
                startActivity(intent2);
            }
        });
        taskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LastActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}