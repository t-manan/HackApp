package com.example.youtube;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @POST("/patient/qrcode")
    Call<QrResponse> generateQr(@Body HashMap<String, String> map);

    @GET("/orders")
    Call<Map<String, Object>> getOrders();
}
