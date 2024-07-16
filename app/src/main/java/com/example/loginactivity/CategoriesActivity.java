package com.example.loginactivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.loginactivity.Room.Category;
import com.example.loginactivity.Room.Dao;
import com.example.loginactivity.Room.MyRoomDb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

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

        addBtn = findViewById(R.id.add_item);
        catName = findViewById(R.id.category_name);

        CatDesc = findViewById(R.id.desc);

        CatQhs = findViewById(R.id.Qhs);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();

              //  Intent intent = new Intent(CategoriesActivity.this,ShowCategoriesActivity.class);
             //   startActivity(intent);
            }

        });


    }




//    private void addCategory() {
//        if(!Valid()){
//            return;
//        } else {
//
//            String name = catName.getText().toString();
//            String desc = CatDesc.getText().toString();
//            String qhsStr = CatQhs.getText().toString();
//            int qhs = Integer.parseInt(qhsStr);
//
//            Category category = new Category(name, desc, qhs);
//            MyRoomDb.getInstance(this).dao().insert(category);
//            List<Category> x = new ArrayList<>();
//            x = MyRoomDb.getInstance(this).dao().getAll();
//            Log.d("RoomDB", "Inserting data: " + category.toString());
//
//
//
//        }

    private void addCategory() {
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
            Category category = new Category(name, desc, qhs);
            MyRoomDb.getInstance(this).dao().insert(category);
            List<Category> categories = new ArrayList<>();
            categories = MyRoomDb.getInstance(this).dao().getAll();
           // List<Category> x = MyRoomDb.getInstance(this).dao().getAll();
            Log.d("RoomDB", "Inserting data: " + category.toString());
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

