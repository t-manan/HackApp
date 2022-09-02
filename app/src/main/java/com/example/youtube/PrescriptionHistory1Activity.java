package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PrescriptionHistory1Activity extends AppCompatActivity {

    ImageButton arrow;
    LinearLayout hiddenView;
    CardView cardView;

    ImageButton arrow1;
    LinearLayout hiddenView1;
    CardView cardView1;

    ImageButton arrow2;
    LinearLayout hiddenView2;
    CardView cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_history1);
        findViewById(R.id.fixed_layout2).setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        Boolean addPrescription = extras.getBoolean("addPrescription");
        cardView = findViewById(R.id.base_cardview);
        arrow = findViewById(R.id.arrow_button);
        hiddenView = findViewById(R.id.hidden_view);
        if(addPrescription)
        {
            findViewById(R.id.fixed_layout2).setVisibility(View.VISIBLE);
        }

        arrow.setOnClickListener(view -> {
            // If the CardView is already expanded, set its visibility
            // to gone and change the expand less icon to expand more.
            if (hiddenView.getVisibility() == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.GONE);
                arrow.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24);
            }

            // If the CardView is not expanded, set its visibility to
            // visible and change the expand more icon to expand less.
            else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
                arrow.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24);
            }
        });

        cardView1 = findViewById(R.id.base_cardview1);
        arrow1 = findViewById(R.id.arrow_button1);
        hiddenView1 = findViewById(R.id.hidden_view1);

        arrow1.setOnClickListener(view -> {
            // If the CardView is already expanded, set its visibility
            // to gone and change the expand less icon to expand more.
            if (hiddenView1.getVisibility() == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenView1.setVisibility(View.GONE);
                arrow1.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24);
            }

            // If the CardView is not expanded, set its visibility to
            // visible and change the expand more icon to expand less.
            else {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenView1.setVisibility(View.VISIBLE);
                arrow1.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24);
            }
        });
        cardView2 = findViewById(R.id.base_cardview2);
        arrow2 = findViewById(R.id.arrow_button2);
        hiddenView2 = findViewById(R.id.hidden_view2);

        arrow2.setOnClickListener(view -> {
            // If the CardView is already expanded, set its visibility
            // to gone and change the expand less icon to expand more.
            if (hiddenView2.getVisibility() == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hiddenView2.setVisibility(View.GONE);
                arrow2.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24);
            }

            // If the CardView is not expanded, set its visibility to
            // visible and change the expand more icon to expand less.
            else {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenView2.setVisibility(View.VISIBLE);
                arrow2.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24);
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id != null)
                {
                    findViewById(R.id.fixed_layout2).setVisibility(View.GONE);
                    Intent camera = new Intent(PrescriptionHistory1Activity.this, CameraActivity.class);
                    startActivity(camera);
                }
                else
                {
                    Intent form = new Intent(PrescriptionHistory1Activity.this, AddPrescriptionActivity.class);
                    startActivity(form);
                }
            }
        });

    }
}