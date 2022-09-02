package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phoneNumber = (EditText)findViewById(R.id.et_phone_number);
                String phoneNumberString = phoneNumber.getText().toString();
                startPinActivity(phoneNumberString);
                Intent i = new Intent(LoginActivity.this, PrescriptionHistoryActivity.class);
                startActivity(i);
            }
        });
    }
    private void startPinActivity(String phoneNumber) {
        Intent intent = new Intent(LoginActivity.this, PinActivity.class);
        intent.putExtra("number", phoneNumber);
        startActivity(intent);
    }
}