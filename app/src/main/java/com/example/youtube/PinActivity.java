package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.youtube.models.LoginRequest;
import com.example.youtube.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PinActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://qrcodess.herokuapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        Bundle extras = getIntent().getExtras();
        String phoneNumber = extras.getString("number");
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        findViewById(R.id.btn_verify_pin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pin = (EditText)findViewById(R.id.otpView);
                String pinString = pin.getText().toString();
                signIn(phoneNumber, pinString);
            }
        });
    }

    private void signIn(String phoneNumber, String pinString) {
        LoginRequest loginRequest = new LoginRequest(phoneNumber, pinString);
        Call<LoginResponse> call = retrofitInterface.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                String typeOfLogin = response.body().type;
                if(typeOfLogin.equals("Patient")) {
                    changeActivity(PatientHomeScreenActivity.class, response);
                }
                else if(typeOfLogin.equals("Doctor")) {
                    changeActivity(DoctorHomeScreenActivity.class, response);
                }
                else if(typeOfLogin.equals("Pharma")) {
                    changeActivity(ChemistHomeScreenActivity.class, response);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(PinActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void changeActivity(Class className, Response<LoginResponse> response) {
        Intent intent = new Intent(PinActivity.this, className);
        intent.putExtra("id", response.body().id);
        startActivity(intent);
    }
}
