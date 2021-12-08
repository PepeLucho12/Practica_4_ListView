package com.example.practica_3_ui_webservices.Interfaz;


import com.example.practica_3_ui_webservices.Modelos.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api_User {
    @GET("public/v1/users?name={name}")
    public Call<User> find(@Path("name") String name);
}
