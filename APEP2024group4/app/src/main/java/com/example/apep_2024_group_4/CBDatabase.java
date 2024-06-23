package com.example.apep_2024_group_4;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserTable.class}, version =1 )
public abstract class CBDatabase extends RoomDatabase {
    private static final String dbname = "user";
    private static CBDatabase  cbDatabase;

    public static synchronized CBDatabase getCbDatabase(Context context){

        if(cbDatabase == null){
            cbDatabase = Room.databaseBuilder(context,CBDatabase.class, dbname)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return cbDatabase;
    }
    public abstract UserDao userDao();
}
