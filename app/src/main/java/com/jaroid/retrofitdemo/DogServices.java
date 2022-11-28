package com.jaroid.retrofitdemo;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogServices {

    @GET("breeds/list/all")
    Call<JsonObject> getAllDog();

    @GET("breeds/list/all")
    Call<ListDogModel> getAllDogModel();

    @GET("breeds/image/random")
    Call<ImageDogModel> getImageDogRandom();
}
