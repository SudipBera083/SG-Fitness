package com.bonggohriday.bonggofitness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class dashboard extends AppCompatActivity {
    ImageView users;
    ImageView catagories;
    ImageView workouts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_dashboard);
        users = findViewById(R.id.imageView4);
        workouts =findViewById(R.id.imageView3);
        catagories = findViewById(R.id.cat_img145);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, users_details.class));

            }
        });


        workouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, AddingWorkouts.class));
            }
        });

        catagories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, page.class));
            }
        });

    }
}