package com.example.youtube;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class DoctorHomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_screen);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        findViewById(R.id.prescription).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(DoctorHomeScreenActivity.this);
                intentIntegrator.setDesiredBarcodeFormats((IntentIntegrator.QR_CODE));
                intentIntegrator.initiateScan();
            }
        });

        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHomeScreenActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
//        String contents = result.getContents();
        if (result != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Would you like to go to Prescription");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Continue with delete operation
                    Intent intent =new Intent(DoctorHomeScreenActivity.this, PrescriptionHistory1Activity.class);
                    intent.putExtra(SearchManager.QUERY,result.getContents());
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("No", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}