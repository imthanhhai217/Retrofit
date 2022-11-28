package com.jaroid.retrofitdemo;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogServices {

    @GET("breeds/list/all")
    Call<JsonObject> getAllDog();

    @GET("breeds/list/all")
    Call<ListDogModel> getAllDogModel();

    @GET("breeds/image/random")
    Call<ImageDogModel> getImageDogRandom();


    @GET("breed/{dog_breeds}/images/random")
    Call<ImageDogModel> getImageDogRandom(@Path("dog_breeds") String dogBreeds);
}
