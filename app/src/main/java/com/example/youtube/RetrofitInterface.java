package com.example.youtube;

import com.example.youtube.models.LoginRequest;
import com.example.youtube.models.LoginResponse;
import com.example.youtube.models.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @GET("/")
    Call<LoginResult> checkConnection();

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/patient/qrcode")
    Call<QrResponse> generateQr(@Body HashMap<String, String> map);

}
