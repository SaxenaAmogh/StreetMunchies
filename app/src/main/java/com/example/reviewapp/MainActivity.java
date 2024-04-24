package com.example.reviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGetOTPClicked(View view) {
        TextInputEditText phoneEditText = findViewById(R.id.phoneEditText);
        String email = (Objects.requireNonNull(phoneEditText.getText())).toString();
        if (isValidEmail(email)) {
            Intent intent = new Intent(this,MainPage.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void onGoogleLoginClicked(View view) {
    }
    public void onFacebookLoginClicked(View view) {
        // Handle the click event for Facebook login/signup
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);
    }

    //public void onTwitterLoginClicked(View view) {
    // Handle the click event for Twitter login/signup
    //}

}