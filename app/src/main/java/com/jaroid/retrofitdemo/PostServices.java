package com.jaroid.retrofitdemo;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostServices {

    @GET("posts")
    Call<ArrayList<PostResponse>> getAllPost();

    @GET("posts")
    Call<String> getAllPostString();

    @GET("posts/{id}")
    Call<PostResponse> getPostById(@Path("id") int id);
}
