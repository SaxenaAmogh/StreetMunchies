package com.example.reviewapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReviewPage extends AppCompatActivity {

    public int id;
    public String location, addressLine;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);


        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Perform actions based on the selected rating
                Toast.makeText(ReviewPage.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        TextView locate = findViewById(R.id.foodlocation);
        ImageView photo = findViewById(R.id.foodimage);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.211.238:5000/get_data").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(ReviewPage.this, "Data not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        id = jsonObject.getInt("id");
                        String imageBase64 = jsonObject.getString("image");
                        location = jsonObject.getString("location");

                        String[] parts = location.split(",");
                        double latitude = Double.parseDouble(parts[0].trim());
                        double longitude = Double.parseDouble(parts[1].trim());

                        byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        runOnUiThread(() -> {
                            photo.setImageBitmap(bitmap);
                            getAddressFromLocation(ReviewPage.this, latitude, longitude);
                            locate.setText((addressLine));
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void add_data(View view){

        EditText foodname = findViewById(R.id.foodname);
        EditText titleEditText = findViewById(R.id.title);
        EditText reviewEditText = findViewById(R.id.review);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        OkHttpClient okHttpClient = new OkHttpClient();

        String name = foodname.getText().toString().trim();
        String title = titleEditText.getText().toString().trim();
        String review = reviewEditText.getText().toString().trim();
        float rating = ratingBar.getRating();

        RequestBody formbody = new FormBody.Builder()
                .add("foodname", name)
                .add("title", title)
                .add("review", review)
                .add("rating", String.valueOf(rating))
                .add("id", String.valueOf(id))
                .build();

        Request request = new Request.Builder().url("http://192.168.211.238:5000/add").post(formbody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ReviewPage.this, e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ReviewPage.this, "Review saved successfully", Toast.LENGTH_SHORT).show();
                        // Clear the EditText fields and reset the RatingBar
                        Intent intent = new Intent(ReviewPage.this, Activity_Myreview.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void getAddressFromLocation(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                addressLine = address.getAddressLine(0); // Complete address

                Log.d("Location", "Address: " + addressLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Location", "Error getting address: " + e.getMessage());
        }
    }

    public void go_back(View view){
        onBackPressed();
    }
}
