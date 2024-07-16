package com.example.loginactivity.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "categories")

public class Category {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    int Id;
    @ColumnInfo(name = "qhs")
    public  int qhs;
    @ColumnInfo (name = "name")
    public String name;
    @ColumnInfo(name = "desc") public  String desc;


    public Category(String name, String desc,int qhs){
        this.name = name;
        this.desc = desc;
        this.qhs = qhs;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getQhs() {
        return qhs;
    }
}
