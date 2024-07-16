package com.example.loginactivity.Room;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.loginactivity.Room.Category;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM categories")

    List<Category> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    default void insert(Category category){

    }



}
