package com.example.loginactivity;

import androidx.room.Entity;



public class DataClassCategory {

    String name;
    String desc;
    int qhs;

    public DataClassCategory(String name, String desc,int qhs){
        this.name = name;
        this.desc = desc;
        this.qhs = qhs;
    }
}

