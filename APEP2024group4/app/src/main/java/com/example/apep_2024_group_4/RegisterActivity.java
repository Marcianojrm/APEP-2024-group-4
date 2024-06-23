package com.example.apep_2024_group_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {

    EditText userName, password;
    Button register;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.login_user_et_id);
        password = findViewById(R.id.login_pass_et_id);
        register = findViewById(R.id.login_log_btn_text);
        login = findViewById(R.id.login_reg_btn_text);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserTable userTable = new UserTable();
                userTable.setUserName(userName.getText().toString());
                userTable.setPassword(password.getText().toString());
                if (validateInput(userTable)){
                CBDatabase cbDatabase = CBDatabase.getCbDatabase(getApplicationContext());
                final UserDao userDao = cbDatabase.userDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        userDao.insertuser(userTable);
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(),"User created", Toast.LENGTH_LONG).show());

                    }
                }).start();
                } else  {
                    Toast.makeText(getApplicationContext(), "Ongeldig", Toast.LENGTH_LONG).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        }

        private Boolean validateInput(UserTable userTable){
        if (userTable.getUserName().isEmpty() ||
            userTable.getPassword().isEmpty()){
            return false;
            }
        return true;
        }
    }
