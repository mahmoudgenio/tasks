package com.example.daw2tables;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test2tables.BarcodeDetails;
import com.example.test2tables.ItemDetails;

import java.util.List;

@Dao
public interface BarcodeDao {
    @Insert
    void insert(BarcodeDetails barcode);

//    @Query("SELECT * FROM BarcodeDetails WHERE categoryId = :id")
//    List<ItemDetails> getItemsForCategory(int id);

    @Query("SELECT * FROM BarcodeDetails")
    List<BarcodeDetails> getAll();

    @Query("Delete from BarcodeDetails")
    void deleteAll();

//    @Query("DELETE FROM sqlite_sequence WHERE name = 'itemSpinner'")
//    void resetItemPrimaryKey();
}
