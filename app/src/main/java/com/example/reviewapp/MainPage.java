package com.example.reviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Intent emailintent = getIntent();
        String email = emailintent.getStringExtra("email");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    // Handle Home button click
                    return true;
                } else if (item.getItemId() == R.id.menu_capture) {
                    // Handle Capture button click
                    Intent intent = new Intent(MainPage.this, PhotoCaptureActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    // Handle Profile button click
                    Intent intent = new Intent(MainPage.this, Activity_Profile.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    public void open_one(View view){
        Intent intent = new Intent(MainPage.this, ramladdoo.class);
        startActivity(intent);
    }
}
