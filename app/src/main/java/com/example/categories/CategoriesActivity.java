package com.example.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Room.MyRoomDb;
import com.example.loginactivity.R;
import com.example.test2tables.CategoryNameSpinner;
import com.example.test2tables.ItemDetails;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    Button addBtn;
    Button addCat;
    EditText catName;
    EditText CatDesc;
    EditText CatQhs;
    private MyRoomDb myRoomDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        initViews();


    }

    public void initViews(){
        addBtn = findViewById(R.id.add_item);
        addCat = findViewById(R.id.add_category);
        catName = findViewById(R.id.category_name);

        CatDesc = findViewById(R.id.desc);

        CatQhs = findViewById(R.id.Qhs);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItems();
            }

        });

        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }

        });
    }

    public void addCategory(){
        if (!ValidCate()) {
            return;
        }
        String name = catName.getText().toString();
        try {


            //CategoryNameSpinner category = MyRoomDb.getInstance(this).daoCat().getCategoryByName(name);

            if (isCategoryExists(name)) {
                Toast.makeText(this, "Category already exists", Toast.LENGTH_SHORT).show();
                return;
            }else {
                CategoryNameSpinner category = new CategoryNameSpinner(name);
               // category.name = name;
                MyRoomDb.getInstance(this).daoCat().insert(category);

                List<CategoryNameSpinner> categories = new ArrayList<>();
                categories = MyRoomDb.getInstance(this).daoCat().getAllCategories();
                Log.d("RoomDB", "Inserting data: " + category.toString());
                // List<Category> x = MyRoomDb.getInstance(this).dao().getAll();
            }
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            Log.e("AddCategory", "Exception: " + e.getMessage());
            // Optionally, you can show a generic error message to the user
            Toast.makeText(this, "Error occurred while adding category", Toast.LENGTH_SHORT).show();
        }

    }



    private boolean isCategoryExists(String catName) {
        CategoryNameSpinner category = MyRoomDb.getInstance(this).daoCat().getCategoryByName(catName);
        return category != null;
    }

    private int isCatIdExists(String catName) {
        CategoryNameSpinner category = MyRoomDb.getInstance(this).daoCat().getIdByName(catName);
        return category.getId();
    }




    private void addItems() {
        if (!Valid()) {
            return;
        }

        String name = catName.getText().toString();
        String desc = CatDesc.getText().toString();
        String qhsStr = CatQhs.getText().toString();
        int qhs = 0; // Initialize qhs with a default value

        try {
            qhs = Integer.parseInt(qhsStr);
        } catch (NumberFormatException e) {
            // Handle the case where qhsStr cannot be parsed into an integer
            Log.e("AddCategory", "NumberFormatException: " + e.getMessage());
            // Optionally, you can show an error message to the user
            Toast.makeText(this, "Invalid quantity value", Toast.LENGTH_SHORT).show();
            return; // Exit method early if parsing fails
        }

        try {

            CategoryNameSpinner category = MyRoomDb.getInstance(this).daoCat().getCategoryByName(name);

           int Id = isCatIdExists(name);

            ItemDetails categoryItem = new ItemDetails(desc,qhs , Id);

            //categoryItem.setId(Id);

            MyRoomDb.getInstance(this).daoItem().insert(categoryItem);


            CategoryNameSpinner category1 = new CategoryNameSpinner(name);

                List<CategoryNameSpinner> categories = new ArrayList<>();
                    categories = MyRoomDb.getInstance(this).daoCat().getAllCategories();

            List<ItemDetails> Items = new ArrayList<>();
            Items = MyRoomDb.getInstance(this).daoItem().getAll();
                    Log.d("RoomDB", "Inserting data: " + Items.toString());

        } catch (Exception e) {
            // Catch any other unexpected exceptions
            Log.e("AddCategory", "Exception: " + e.getMessage());
            // Optionally, you can show a generic error message to the user
            Toast.makeText(this, "Error occurred while adding category", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean ValidCate() {
        if(catName.getText().toString().isBlank()){

            catName.setError("enter Category Name");
        }
        return true;
    }




    private boolean Valid() {
        if(catName.getText().toString().isBlank()){

            catName.setError("enter Category Name");
        }

        else if(CatDesc.getText().toString().isBlank()){

            CatDesc.setError("enter Category Descr");
        }

       else if(CatQhs.getText().toString().isBlank()){

            CatQhs.setError("enter Category Qhs");
        }
        return true;
    }
    


}

