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
import com.example.Api.RetrofitService;
import com.example.loginactivity.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void getAllUsers(){

        RetrofitService.Client.getInstance().getAllUsers().enqueue(new Callback<List<AllUsers>>() {
            @Override
            public void onResponse(Call<List<AllUsers>> call, Response<List<AllUsers>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AllUsers>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"server error",Toast.LENGTH_LONG).show();
            }

        });

    }
}