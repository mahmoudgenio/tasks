package com.example.loginactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.Room.Category;
import com.example.loginactivity.Room.MyRoomDb;

import java.util.ArrayList;
import java.util.List;

public class ShowCategoriesActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CategoruRecyclerAdapter adapter;
    //private List<Category> categories;
    private List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);
        recyclerView = findViewById(R.id.recycler);
        categories = new ArrayList<>();

        List<Category> categoriesList = MyRoomDb.getInstance(ShowCategoriesActivity.this).dao().getAll();
        categories.clear();
      //  categoriesList.add(new Category("Category 2", "Description 2", 5));
        //categories.addAll(categoriesList);
        if (categoriesList != null && !categoriesList.isEmpty()) {
            Log.d("RoomDB", "Categories retrieved successfully. Count: " + categoriesList.size());
            categories.addAll(categoriesList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            adapter = new CategoruRecyclerAdapter(this, categoriesList);
            recyclerView.setAdapter(adapter);
        } else {
            Log.d("RoomDB", "No categories found in database.");
        }


    }


    @Override
    protected void onStart() {
        super.onStart();

    }


}
