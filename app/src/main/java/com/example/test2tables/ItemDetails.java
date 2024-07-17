package com.example.test2tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "itemSpinner")

public class ItemDetails {


    @NotNull
//    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "qhs")
    public  int qhs;
    @ColumnInfo(name = "desc") public  String desc;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryId")  public int categoryId;




    public ItemDetails(String desc, int qhs){
        this.desc = desc;
        this.qhs = qhs;


    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getDescription() {
        return desc;
    }

    public int getQuantity() {
        return qhs;
    }

//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }

}
