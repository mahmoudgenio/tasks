package com.example.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Api.CategoriesResponse;
import com.example.Api.ItemsResponse;
import com.example.Api.RetrofitService;
import com.example.Room.MyRoomDb;
import com.example.loginactivity.R;
import com.example.test2tables.CategoryNameSpinner;
import com.example.test2tables.ItemDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesActivity extends AppCompatActivity {

    Button addBtn;
    ProgressBar load;
    Button addRetrofit;
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
        addRetrofit = findViewById(R.id.add_categories_retrofit);
        addCat = findViewById(R.id.add_category);
        catName = findViewById(R.id.category_name);
        load = findViewById(R.id.progress_bar);

        CatDesc = findViewById(R.id.desc);

        CatQhs = findViewById(R.id.Qhs);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItems();
            }

        });

        addRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.setVisibility(View.VISIBLE);
                insertAllCategoriesToRoom();
                insertAllItemsToRoom();

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        load.setVisibility(View.GONE);
                    }
                }, 3000);
            }
//                insertAllCategoriesToRoom();
               // Toast.makeText(getApplicationContext(),"success insert from retrofit to room",Toast.LENGTH_LONG).show();
//                load.setVisibility(View.GONE);
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


    public void insertAllCategoriesToRoom(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.77:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService apiService = retrofit.create(RetrofitService.class);
        Call<List<CategoriesResponse>> call = apiService.getAllCategories();
        call.enqueue(new Callback<List<CategoriesResponse>>() {
            @Override
            public void onResponse(Call<List<CategoriesResponse>> call, Response<List<CategoriesResponse>> response) {
                List<CategoriesResponse> allCategoriesList = response.body();
                if (response.isSuccessful() && response.code() == 200) {
                    int limit = 10;

                    int count = 0;

                    for (CategoriesResponse Category : allCategoriesList) {
                        if (count >= limit) {

                            break;
                        }


                        String catName = Category.getCategoryID();  // name


                        try {

                            if (isCategoryExists(catName)) {
                                Toast.makeText(getApplicationContext(), "Category already exists", Toast.LENGTH_SHORT).show();

                            }else {
                                CategoryNameSpinner category = new CategoryNameSpinner(catName);
                                MyRoomDb.getInstance(getApplicationContext()).daoCat().insert(category);

                                // get Items
                                //check cat Exist or not
                                // if found get catId and insert item
                               // insertAllItemsToRoom(catName);

                            }
                        } catch (Exception e) {
                            // Catch any other unexpected exceptions
                            Log.e("AddCategory", "Exception: " + e.getMessage());
                            // Optionally, you can show a generic error message to the user
                            Toast.makeText(getApplicationContext(), "Error occurred while adding category", Toast.LENGTH_SHORT).show();
                        }
                        count++;

                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoriesResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void insertAllItemsToRoom(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.77:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService apiService = retrofit.create(RetrofitService.class);
        Call<List<ItemsResponse>> call = apiService.getAllItems();
        call.enqueue(new Callback<List<ItemsResponse>>() {
            @Override
            public void onResponse(Call<List<ItemsResponse>> call, Response<List<ItemsResponse>> response) {


                if (response.isSuccessful() && response.code() == 200) {
                    List<ItemsResponse> allItemsList = response.body(); // getItems

                    int limit = 10;
                    int count = 0;
                    for (ItemsResponse Item : allItemsList) {
                        if (count >= limit) {
                            break;
                        }


                       // String id = Item.getCategoryID();
                       // CategoryNameSpinner category1 = MyRoomDb.getInstance(getApplicationContext()).daoCat().getCategoryByName(name);


//                        if(!isCategoryExists(name)){
//
//                            Toast.makeText(getApplicationContext(), "cat not found", Toast.LENGTH_SHORT).show();
//                        }


                        int Id = isCatIdExists(Item.getCategoryID());
                       // int catId = category1.getId();  //2

                        String desc = Item.getItemDesc();
                        int qhs = Item.getItemID(); // qhs

                        try {

                               // ItemDetails item = new ItemDetails(desc,qhs,Id);
                            ItemDetails item = new ItemDetails(desc,qhs,Id);
                                MyRoomDb.getInstance(getApplicationContext()).daoItem().insert(item);


                        } catch (Exception e) {
                            // Catch any other unexpected exceptions
                            Log.e("AddCategory", "Exception: " + e.getMessage());
                            // Optionally, you can show a generic error message to the user
                            Toast.makeText(getApplicationContext(), "Error occurred while adding category", Toast.LENGTH_SHORT).show();
                        }
                        count++;


                    }



                }
            }


            @Override
            public void onFailure(Call<List<ItemsResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }





}

