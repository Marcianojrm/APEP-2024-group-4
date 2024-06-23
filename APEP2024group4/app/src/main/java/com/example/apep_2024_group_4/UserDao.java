package com.example.apep_2024_group_4;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insertuser(UserTable userTable);

    @Query("SELECT * from users where userName=(:userName) and password=(:password)")
    UserTable login(String userName, String password);

}
