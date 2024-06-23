package com.example.apep_2024_group_4;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserTable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userName")
    private String userName;

    @ColumnInfo(name = "password")
    private String password;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
