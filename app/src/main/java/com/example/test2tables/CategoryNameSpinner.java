package com.example.test2tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "categorySpinner")

public class CategoryNameSpinner {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo (name = "name")
    public String name;


    public CategoryNameSpinner(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

}



