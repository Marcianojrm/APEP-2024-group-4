package com.example.apep_2024_group_4;

import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {


    EditText userName, password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.login_user_et_id);
        password = findViewById(R.id.login_pass_et_id);
        login = findViewById(R.id.login_log_btn_text);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNametext = userName.getText().toString();
                String passwordText = password.getText().toString();
                if (userNametext.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
                } else {

                    CBDatabase cbDatabase = CBDatabase.getCbDatabase(getApplicationContext());
                    UserDao userDao = cbDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserTable userTable = userDao.login(userNametext, passwordText);
                            if (userTable == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"Invalid!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                String name = userTable.getUserName();
                                startActivity(new Intent
                                        (LoginActivity.this, OverviewActivity.class)
                                        .putExtra("name",name));
                            }
                        }
                    }).start();
                }
            }
        });
    }
}