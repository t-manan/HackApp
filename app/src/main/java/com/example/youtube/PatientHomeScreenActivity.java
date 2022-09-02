package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PatientHomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_screen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileActivity = new Intent(PatientHomeScreenActivity.this, ProfileActivity.class);
                startActivity(profileActivity);
            }
        });
        findViewById(R.id.prescription).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileActivity = new Intent(PatientHomeScreenActivity.this, PrescriptionHistory1Activity.class);
                profileActivity.putExtra("id", id);
                startActivity(profileActivity);
            }
        });
        findViewById(R.id.appointment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientHomeScreenActivity.this, "Coming Soon!", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.healthreports).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientHomeScreenActivity.this, "Coming Soon!", Toast.LENGTH_LONG).show();
            }
        });
    }
}