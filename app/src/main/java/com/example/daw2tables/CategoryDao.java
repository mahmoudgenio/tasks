package com.example.daw2tables;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
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

    @Query("SELECT * FROM CategorySpinner WHERE name = :name ")
    CategoryNameSpinner getCategoryByName(String name);

    @Query("SELECT * FROM CategorySpinner WHERE name = :name ")
    CategoryNameSpinner getIdByName(String name);

    @Query("Delete from CategorySpinner")
    void deleteAll();

    @Query("SELECT * FROM CategorySpinner WHERE id = :id LIMIT 1")
    CategoryNameSpinner getCategoryById(int id);

    @Query("DELETE FROM sqlite_sequence WHERE name = 'categorySpinner'")
    void resetPrimaryKey();



}
