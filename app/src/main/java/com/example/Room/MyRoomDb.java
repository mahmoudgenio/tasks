package com.example.Room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.daos.Dao;
import com.example.daw2tables.CategoryDao;
import com.example.daw2tables.ItemDao;
import com.example.tables.Category;
import com.example.test2tables.CategoryNameSpinner;
import com.example.test2tables.ItemDetails;

@Database(entities = {Category.class , CategoryNameSpinner.class , ItemDetails.class},version = 43,exportSchema = false)

public abstract class MyRoomDb extends RoomDatabase {

    public abstract Dao dao();
    public abstract CategoryDao daoCat();
    public abstract ItemDao daoItem();

    private android.content.Context Context;

    public static MyRoomDb getInstance(android.content.Context context) {
        return Room.databaseBuilder(context, MyRoomDb.class, "MyRoomDb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
