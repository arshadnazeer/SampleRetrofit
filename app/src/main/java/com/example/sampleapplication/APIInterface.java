package com.example.sampleapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface APIInterface {

    //pass value
    @GET("posts")
    Call<List<POJO>> getPosts(@Query("userId") Integer[] userId,
                              @Query("_sort") String sort,
                              @Query("_order") String desc
                              );

    //pass by HashMap
    @GET("posts")
    Call<List<POJO>> getPosts(@QueryMap Map<String, String> parameters);


    //pass value
    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

    //pass url
    @GET
    Call<List<Comments>> getComments(@Url String url);
}
