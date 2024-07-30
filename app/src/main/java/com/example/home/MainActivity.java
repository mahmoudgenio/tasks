package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Api.AllUsers;
import com.example.Api.CategoriesResponse;
import com.example.Api.RetrofitService;
import com.example.loginactivity.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                getAllUsers();

            }
        });

    }


    public void getAllUsers(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.77:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService apiService = retrofit.create(RetrofitService.class);
        Call<List<AllUsers>> call = apiService.getAllUsers();
        call.enqueue(new Callback<List<AllUsers>>() {
            @Override
            public void onResponse(Call<List<AllUsers>> call, Response<List<AllUsers>> response) {
                List<AllUsers> allUsersList = response.body();
                if (response.isSuccessful() && response.code() == 200) {
                    String name = editTextName.getText().toString();
                    String pass = editTextPass.getText().toString();

                    for (AllUsers user : allUsersList) {
                        String userName = user.getUserName();
                        String password = user.getPassword();

                        if(name.equals(userName) && pass.equals(password)){
                            Toast.makeText(MainActivity.this,"Success Login",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("name" , name);
                            startActivity(intent);

                        }

                    }

                    Toast.makeText(getApplicationContext(), "successfully get AllUsers", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<AllUsers>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }