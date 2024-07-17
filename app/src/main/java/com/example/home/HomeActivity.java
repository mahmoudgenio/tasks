package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.categories.CategoriesActivity;
import com.example.categories.ShowCategoriesActivity;
import com.example.loginactivity.R;

public class HomeActivity extends AppCompatActivity {
    TextView textName;
    ImageView category1;
    ImageView category2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textName = findViewById(R.id.data);
        category1 = findViewById(R.id.img1);
        category2 = findViewById(R.id.img2);
        Intent intent= getIntent();
       String getName = intent.getStringExtra("name");
        textName.setText(getName);


        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, CategoriesActivity.class);
                startActivity(intent);

            }

        });


        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, ShowCategoriesActivity.class);
                startActivity(intent);

            }

        });
    }


}