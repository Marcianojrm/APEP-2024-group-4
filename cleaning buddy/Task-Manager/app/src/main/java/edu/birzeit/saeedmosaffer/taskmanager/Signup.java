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
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signup extends AppCompatActivity {
    private EditText etUsername, etPassword, etRepeatPassword;
    private AppCompatButton btnSignup;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String repeatPassword = etRepeatPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                    Toast.makeText(Signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(repeatPassword)) {
                    Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(Signup.this, "Signup successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Signup.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}