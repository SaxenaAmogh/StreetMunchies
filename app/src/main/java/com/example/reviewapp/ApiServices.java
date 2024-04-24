package com.example.reviewapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("upload_image")
    Call<Void> uploadImage(@Body String imageBase64);
}
