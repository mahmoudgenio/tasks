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
import com.example.tables.Category;
import com.example.test2tables.CategoryNameSpinner;
import com.example.test2tables.ItemDetails;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

Button addBtn;
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
        catName = findViewById(R.id.category_name);

        CatDesc = findViewById(R.id.desc);

        CatQhs = findViewById(R.id.Qhs);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }

        });

    }


    private void addCategory() {
        if (!Valid()) {
            return;
        }

        int catId = 0;
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

           // Category category = new Category(name, desc, qhs);  ==
            ItemDetails categoryItem = new ItemDetails(desc,qhs);

            MyRoomDb.getInstance(this).daoItem().insert(categoryItem);
            List<ItemDetails> categoriesItem = new ArrayList<>();
            categoriesItem = MyRoomDb.getInstance(this).daoItem().getItemsForCategory(catId);


            CategoryNameSpinner category = new CategoryNameSpinner(name);
                MyRoomDb.getInstance(this).daoCat().insert(category);

                List<CategoryNameSpinner> categories = new ArrayList<>();
                    categories = MyRoomDb.getInstance(this).daoCat().getAllCategories();
                    Log.d("RoomDB", "Inserting data: " + category.toString());
           // List<Category> x = MyRoomDb.getInstance(this).dao().getAll();

        } catch (Exception e) {
            // Catch any other unexpected exceptions
            Log.e("AddCategory", "Exception: " + e.getMessage());
            // Optionally, you can show a generic error message to the user
            Toast.makeText(this, "Error occurred while adding category", Toast.LENGTH_SHORT).show();
        }
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

