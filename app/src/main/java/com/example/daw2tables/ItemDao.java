package com.example.daw2tables;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test2tables.ItemDetails;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insert(ItemDetails item);

    @Query("SELECT * FROM itemSpinner WHERE categoryId = :id")
    List<ItemDetails> getItemsForCategory(int id);

    @Query("SELECT * FROM itemSpinner")
    List<ItemDetails> getAll();

    @Query("Delete from itemSpinner")
    void deleteAll();

}
