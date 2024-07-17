package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginactivity.R;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPass;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        editTextName = findViewById(R.id.user_name);
        editTextPass = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
               String pass = editTextPass.getText().toString();

                if(name.equals("Mahmoud Mostafa")||pass.equals("1234")){
                    Toast.makeText(MainActivity.this,"Success Login",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("name" , name);
                    startActivity(intent);


                }else{

                    Toast.makeText(MainActivity.this,"invalid login",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}