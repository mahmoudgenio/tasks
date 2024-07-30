package com.example.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public interface RetrofitService {

    @GET("api/Connection/GetAllUsers")
    Call<List<AllUsers>> getAllUsers();



    @GET("api/DownloadData/GetCategories")
    Call<List<CategoriesResponse>> getAllCategories();


    @GET("api/DownloadData/GetAllItems")
    Call<List<ItemsResponse>> getAllItems();




//    public static class Client {
//
//        private static RetrofitService retrofitService = null;
//
//        public static RetrofitService getInstance() {
//            if (retrofitService == null) {
//                // Create Gson instance
//                Gson gson = new GsonBuilder()
//                        .setLenient()
//                        .create();
//
//
//                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//                // Create OkHttpClient instance
////                OkHttpClient client = new OkHttpClient.Builder()
////                        .addInterceptor(logging)
////                        .build();
//
//                // Create Retrofit instance
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://192.168.1.77:8080/")
////                        .client(client)  // Include the OkHttpClient instance
//                        .addConverterFactory(GsonConverterFactory.create(gson))
//                        .build();
//
//                retrofitService = retrofit.create(RetrofitService.class);
//            }
//            return retrofitService;
//        }
//    }
}


