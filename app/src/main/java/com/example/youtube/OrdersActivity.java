package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://qrcodess.herokuapp.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getOrders();
    }

    private void getOrders() {
        Call<Map<String, Object>> call = retrofitInterface.getOrders();
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                Toast.makeText(OrdersActivity.this, response.body().get("orders").toString(),Toast.LENGTH_LONG).show();
                ArrayList<LinkedTreeMap>  f= ((ArrayList) response.body().get("orders"));

                if (response.code() == 200) {
                } else if (response.code() == 400) {
                    Toast.makeText(OrdersActivity.this,
                            "Api Service Issue", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(OrdersActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}