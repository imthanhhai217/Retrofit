package com.jaroid.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private PostServices postServices;
    private PostAdapter postAdapter;
    private RecyclerView rvPost;
    private ArrayList<PostResponse> postResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
//        getPostById(200);

        getAllPost();
    }

    private void initView() {
        rvPost = findViewById(R.id.rvPost);

        postResponses = new ArrayList<>();
        postAdapter = new PostAdapter();
        postAdapter.setData(postResponses);
        rvPost.setAdapter(postAdapter);
    }

    private void initData() {
        postServices = RetrofitClient.getService(ConstantApi.BASE_URL, PostServices.class);
        postResponses = new ArrayList<>();
        postResponses.clear();
    }

    private void getAllPost() {
        rvPost.setVisibility(View.VISIBLE);
        postServices.getAllPost().enqueue(new Callback<ArrayList<PostResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PostResponse>> call, Response<ArrayList<PostResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        ArrayList<PostResponse> data = response.body();
                        if (data != null && data.size() > 0) {
                            //update new data to adapter
                            postAdapter.setData(data);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PostResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
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