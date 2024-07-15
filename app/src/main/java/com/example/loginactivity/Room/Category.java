package com.example.loginactivity.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")

public class Category {

    String name;
    String desc;
    @PrimaryKey
    int qhs;

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
