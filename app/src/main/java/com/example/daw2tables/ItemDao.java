package com.example.daw2tables;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test2tables.ItemDetails;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insert(ItemDetails item);

    @Query("SELECT * FROM itemSpinner WHERE categoryId = :categoryId")
    List<ItemDetails> getItemsForCategory(int categoryId);

}
