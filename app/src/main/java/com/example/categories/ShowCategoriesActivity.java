package com.example.categories;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
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
        loadCategories();


    }

    private void loadCategories() {
            List<CategoryNameSpinner> categories = MyRoomDb.getInstance(this).daoCat().getAllCategories();
                categoryNames.clear();
                categoryIds.clear();
                for (CategoryNameSpinner category : categories) {
                    categoryNames.add(category.getName());
                    categoryIds.add(category.getId());
                }
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.insert("select category", 0);
        categorySpinner.setSelection(0,false);
        categorySpinner.setAdapter(spinnerAdapter);
                ((ArrayAdapter) categorySpinner.getAdapter()).notifyDataSetChanged();


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
                if (selectedCategoryId != 0) {
                    loadItems(selectedCategoryId);
                }

                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }


    private void loadItems(int id) {


        List<ItemDetails> items = MyRoomDb.getInstance(this).daoItem().getItemsForCategory(id);


                itemsAdapter = new ItemsAdapter(items);
                recyclerView.setAdapter(itemsAdapter);
                itemsAdapter.notifyDataSetChanged();

    }








}

