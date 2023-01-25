package com.example.filmoteca;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MyAPI {
    @GET
    Call<String> getData(@Url String url);
}