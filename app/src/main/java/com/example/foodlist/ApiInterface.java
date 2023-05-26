package com.example.foodlist;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("categories.php")
    Call<CategoryResponse> fetchAllRemote();
    @GET("categories.php")
    Call<CategoryResponse1> fetchAllRemote1();

//    @GET("categories.php/{id}")
//    Call<Category> getCategoryItem(@Path("id") String CategoryItemId);




}