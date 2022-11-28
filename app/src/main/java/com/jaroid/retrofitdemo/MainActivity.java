package com.jaroid.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private PostServices postServices;
    private DogServices dogServices;

    private ImageView imgDogRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgDogRandom = findViewById(R.id.imgDogRandom);
        initData();
//        getPostById(200);

//        getAllPost();
//        getAllPostString();
        getAllDog();
        getAllDogModel();
        getImageDogRandom();
    }

    private void getImageDogRandom() {
        dogServices.getImageDogRandom().enqueue(new Callback<ImageDogModel>() {
            @Override
            public void onResponse(Call<ImageDogModel> call, Response<ImageDogModel> response) {

                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        ImageDogModel imageDogModel = response.body();
                        Glide
                                .with(MainActivity.this)
                                .load(imageDogModel.getMessage())
                                .into(imgDogRandom);
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageDogModel> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private void getAllDogModel() {
        dogServices.getAllDogModel().enqueue(new Callback<ListDogModel>() {
            @Override
            public void onResponse(Call<ListDogModel> call, Response<ListDogModel> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        ListDogModel listDogModel = response.body();
                    }
                } else {
                    Log.d(TAG, "onResponse: un Succesful " + response.code() + " | " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<ListDogModel> call, Throwable t) {

            }
        });
    }

    private void getAllDog() {
        dogServices.getAllDog().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        Gson gson = new Gson();
                        ListDogModel listDogModel = gson.fromJson(response.body().toString(), ListDogModel.class);

                        Log.d(TAG, "onResponse: " + listDogModel.getStatus());
                        Log.d(TAG, "onResponse: " + listDogModel.getMessage().getBulldog().toString());
                    }
                } else {
                    Log.d(TAG, "onResponse: un Succesful " + response.code() + " | " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void getAllPostString() {
        postServices.getAllPostString().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: " + response.toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getAllPost() {
        postServices.getAllPost().enqueue(new Callback<ArrayList<PostResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PostResponse>> call, Response<ArrayList<PostResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PostResponse>> call, Throwable t) {

            }
        });
    }

    private void initData() {
//        postServices = RetrofitClient.getService(ConstantApi.BASE_URL, PostServices.class);
        dogServices = RetrofitClient.getService(ConstantApi.BASE_URL_DOG, DogServices.class);
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