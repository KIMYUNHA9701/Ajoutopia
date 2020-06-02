package com.example.crux16_ajou;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitCommunication {

    @POST("/users/login")
    Call<JsonObject> userLogin(@Body JsonObject userData);

}
