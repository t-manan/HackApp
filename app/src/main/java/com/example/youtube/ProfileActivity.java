package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://qrcodess.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.generate_qr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("url", "https://qrcodess.herokuapp.com/basicdetails?id=1");
                generateQr(map);
            }
        });
        findViewById(R.id.qrscanner).setVisibility(View.GONE);
    }

    private void generateQr(HashMap<String,String> map) {
        Call<QrResponse> call = retrofitInterface.generateQr(map);
        call.enqueue(new Callback<QrResponse>() {
            @Override
            public void onResponse(Call<QrResponse> call, Response<QrResponse> response) {
//                Toast.makeText(ProfileActivity.this, response.body().getSource(),Toast.LENGTH_LONG).show();
                if (response.code() == 200) {
                    Intent intent = new Intent(ProfileActivity.this, QrCodeActivity.class);
                    intent.putExtra("source", response.body().getSource());
                    startActivity(intent);
                } else if (response.code() == 400) {
                    Toast.makeText(ProfileActivity.this,
                            "Already registered", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<QrResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}