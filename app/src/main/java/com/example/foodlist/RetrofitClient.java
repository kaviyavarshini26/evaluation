package com.example.foodlist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String baseurl = "https://www.themealdb.com/api/json/v1/1/";
   private static RetrofitClient retrofitClient;
   private static Retrofit retrofit;

  private RetrofitClient(){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        public static synchronized RetrofitClient getInstance(){
    if(retrofitClient==null){
    retrofitClient=new RetrofitClient();


}
return retrofitClient;
}
 public ApiInterface getApi(){
      return retrofit.create(ApiInterface.class);
}
  }

