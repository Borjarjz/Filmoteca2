package com.example.filmoteca;


import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface MyAPI {
    @GET("/pelicula/{id}")
    Call<ResponseBody> getData(@Path("id") int id);
}