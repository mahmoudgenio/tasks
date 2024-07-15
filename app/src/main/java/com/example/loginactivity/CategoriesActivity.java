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

        addBtn = findViewById(R.id.add_item);
        catName = findViewById(R.id.category_name);

        CatDesc = findViewById(R.id.desc);

        CatQhs = findViewById(R.id.Qhs);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();

                Intent intent = new Intent(CategoriesActivity.this,ShowCategoriesActivity.class);
                startActivity(intent);
            }

        });


    }




    private void addCategory() {
        if(!Valid()){
            return;
        } else {

            String name = catName.getText().toString();
            String desc = CatDesc.getText().toString();
            String qhsStr = CatQhs.getText().toString();
            int qhs = Integer.parseInt(qhsStr);

            Category category = new Category(name, desc, qhs);

            MyRoomDb.getInstance(this)
                    .dao().insert(category);


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
        return false;
    }


}

