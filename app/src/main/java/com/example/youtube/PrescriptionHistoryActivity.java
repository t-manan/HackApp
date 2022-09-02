package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PrescriptionHistoryActivity extends AppCompatActivity {

    ImageButton arrow;
    LinearLayout hiddenView;
    CardView cardView;

    ImageButton arrow1;
    LinearLayout hiddenView1;
    CardView cardView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_history);

    }
}