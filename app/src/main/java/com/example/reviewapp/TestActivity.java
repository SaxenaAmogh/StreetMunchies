package com.example.reviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    LinearLayout layout1;
    TextView instr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        layout1 = findViewById(R.id.linearLayout);
        instr2 = findViewById(R.id.instr2);
    }

    public void act1(View view) {
        layout1.setVisibility(View.GONE);
        instr2.setVisibility(View.VISIBLE);
    }

    public void act2(View view) {
        ImageView grey1 = findViewById(R.id.grey1);
        ImageView led1 = findViewById(R.id.led1);
        LinearLayout finalLayout = findViewById(R.id.layout2);

        grey1.setVisibility(View.GONE);
        led1.setVisibility(View.VISIBLE);
        instr2.setVisibility(View.GONE);
        finalLayout.setVisibility(View.VISIBLE);
    }

    public void exit(View view) {
        onBackPressed();
    }
}
