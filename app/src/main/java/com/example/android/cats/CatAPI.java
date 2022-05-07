package com.example.android.cats;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface CatAPI {

    @Headers("x-api-key: 61011175-35b5-4df8-a58f-ee80e87afe2e")
    @GET("v1/breeds")
    Call<List<Cat>> getCats();
}
