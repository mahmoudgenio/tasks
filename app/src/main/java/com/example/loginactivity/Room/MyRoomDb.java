package com.example.loginactivity.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class},version = 2,exportSchema = false)

public abstract class MyRoomDb extends RoomDatabase {

    public abstract Dao dao();

    private android.content.Context Context;

    public static MyRoomDb getInstance(android.content.Context context) {
        return Room.databaseBuilder(context, MyRoomDb.class, "MyRoomDb")
                .fallbackToDestructiveMigrationFrom()
                .allowMainThreadQueries()
                .build();
    }
}
