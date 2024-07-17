package com.example.daw2tables;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test2tables.CategoryNameSpinner;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(CategoryNameSpinner category);

    @Query("SELECT * FROM categorySpinner")
    List<CategoryNameSpinner> getAllCategories();



}
