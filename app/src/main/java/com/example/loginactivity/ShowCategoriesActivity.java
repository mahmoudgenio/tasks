package com.example.loginactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.Room.Category;
import com.example.loginactivity.Room.MyRoomDb;

import java.util.ArrayList;
import java.util.List;

public class ShowCategoriesActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CategoruRecyclerAdapter adapter;
    private List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);
        recyclerView = findViewById(R.id.recycler);
        categoryList = new ArrayList<>();

//        categoryList.add(new Category("zxvv", "sfas",8 ));
//        categoryList.add(new Category("Category 2", "Description 2", 5));

        adapter = new CategoruRecyclerAdapter(this, categoryList);
        recyclerView.setAdapter(adapter);

        // ===============================



    }

    @Override
    protected void onStart() {
        super.onStart();
        loadCategories();
    }

    private void loadCategories() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Retrieve categories from Room database
                List<Category> categories = MyRoomDb.getInstance(ShowCategoriesActivity.this).dao().getAll();

                // Update UI on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // categoryList.clear();
                        categoryList.addAll(categories);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

        });
    }
}