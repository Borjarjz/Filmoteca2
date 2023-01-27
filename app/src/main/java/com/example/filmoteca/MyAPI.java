package com.example.filmoteca;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MyAPI {
    @Headers("Content-Type: application/json")
    @GET("/pelicula/{id}")
    Call<ResponseBody> getData(@Path("id") int id);
}