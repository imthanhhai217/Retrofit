package com.jaroid.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private PostServices postServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
//        getPostById(200);

        getAllPost();
    }

    private void getAllPost() {
        postServices.getAllPost().enqueue(new Callback<ArrayList<PostResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PostResponse>> call, Response<ArrayList<PostResponse>> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PostResponse>> call, Throwable t) {

            }
        });
    }

    private void initData() {
        postServices = RetrofitClient.getService(ConstantApi.BASE_URL, PostServices.class);
    }

    private void getPostById(int id) {
        postServices.getPostById(id).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String log = gson.toJson(response.body());
                    Log.d(TAG, "onResponse: \n" + log);
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}