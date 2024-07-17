package com.example.categories;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Room.MyRoomDb;
import com.example.loginactivity.R;
import com.example.recyclertest.ItemsAdapter;
import com.example.test2tables.CategoryNameSpinner;
import com.example.test2tables.ItemDetails;

import java.util.ArrayList;
import java.util.List;

public class ShowCategoriesActivity extends AppCompatActivity {

    // new spinner
    private List<String> categoryNames;
    private List<Integer> categoryIds;
    private ItemsAdapter itemsAdapter;
    private Spinner categorySpinner;

    //=====
    private RecyclerView recyclerView;
    private CategoruRecyclerAdapter adapter;
    //private List<Category> categories;
   // private List<Category> categories = new ArrayList<>();  ==

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);

        initViews();
        // categories = new ArrayList<>(); ==

        // List<Category> categoriesList = MyRoomDb.getInstance(ShowCategoriesActivity.this).dao().getAll(); ==
        // categories.clear(); ==
        //  categoriesList.add(new Category("Category 2", "Description 2", 5));
        //categories.addAll(categoriesList);
        // if (categoriesList != null && !categoriesList.isEmpty()) { ==
        //   Log.d("RoomDB", "Categories retrieved successfully. Count: " + categoriesList.size());  ==
        // categories.addAll(categoriesList);  ==
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//            recyclerView.setLayoutManager(mLayoutManager);
        // adapter = new CategoruRecyclerAdapter(this, categoriesList);  ==
        //  recyclerView.setAdapter(adapter);  ==
//        } else {
//            Log.d("RoomDB", "No categories found in database.");
//        }


        // new spinner


//        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        categorySpinner.setAdapter(spinnerAdapter);


        loadCategories();


    }

    private void loadCategories() {
        new Thread(() -> {
            List<CategoryNameSpinner> categories = MyRoomDb.getInstance(this).daoCat().getAllCategories();
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(() -> {
                categoryNames.clear();
                categoryIds.clear();
                for (CategoryNameSpinner category : categories) {
                    categoryNames.add(category.getName());
                    categoryIds.add(category.getId());
                }
                ((ArrayAdapter) categorySpinner.getAdapter()).notifyDataSetChanged();
            });
        }).start();
    }


    private void initViews(){
        recyclerView = findViewById(R.id.recycler);
        categorySpinner = findViewById(R.id.category_spinner);
        recyclerView = findViewById(R.id.recycler);

        itemsAdapter = new ItemsAdapter(new ArrayList<>());
        recyclerView.setAdapter(itemsAdapter);

        categoryNames = new ArrayList<>();
        categoryIds = new ArrayList<>();

        MyRoomDb db = MyRoomDb.getInstance(getApplicationContext());


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedCategoryId = categoryIds.get(position);
                loadItems(selectedCategoryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
    }

    private void loadItems(int categoryId) {
        new Thread(() -> {
            List<ItemDetails> items = MyRoomDb.getInstance(this).daoItem().getItemsForCategory(categoryId);
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(() -> {
                itemsAdapter = new ItemsAdapter(items);
                recyclerView.setAdapter(itemsAdapter);
            });
        }).start();
    }





}

