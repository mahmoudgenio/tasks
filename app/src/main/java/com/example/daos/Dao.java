package com.example.daos;

import androidx.room.Insert;
import androidx.room.Query;
import com.example.tables.Category;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM categories")

    List<Category> getAll();

    @Insert
    void insert(Category category);



}
