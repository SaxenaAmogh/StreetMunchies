package com.example.reviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_Profile extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(Activity_Profile.this, MainPage.class);
                    startActivity(intent);
                    // Handle Home button click
                    return true;
                } else if (item.getItemId() == R.id.menu_capture) {
                    // Handle Capture button click
                    Intent intent = new Intent(Activity_Profile.this, PhotoCaptureActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    // Handle Profile button click
                    Intent intent = new Intent(Activity_Profile.this, Activity_Profile.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    public void openReviews(View view){
        Intent intent = new Intent(Activity_Profile.this, Activity_Myreview.class);
        startActivity(intent);
    }

}