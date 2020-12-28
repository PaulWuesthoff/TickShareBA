package com.tickshareba.activities.asaphandling;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.R;

public class ASAPInitialExampleActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ASAPExampleApplication.initializeASAPExampleApplication(this);

        Intent intent = new Intent(this, ASAPExampleActivity.class);
        this.startActivity(intent);
        this.setContentView(R.layout.example_layout);
    }
}
