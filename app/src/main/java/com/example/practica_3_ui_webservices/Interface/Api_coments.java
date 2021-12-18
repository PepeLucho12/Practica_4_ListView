package com.example.practica_3_ui_webservices.Interface;

import com.example.practica_3_ui_webservices.Model.post_Coments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_coments {

    @GET("posts/1/comments")
    Call<List<post_Coments>> getPosts();
}
