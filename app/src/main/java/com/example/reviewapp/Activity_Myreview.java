package com.example.reviewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_Myreview extends AppCompatActivity {
    String location, addressLine;
    public String foodName, foodTitle, foodReview, foodRating;
    public Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        LinearLayout scrollView = findViewById(R.id.MainView);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.211.238:5000/get_review_data").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(Activity_Myreview.this, "Data not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String responseData = response.body().string();
                    Log.d("Response", responseData); // Log the response data
                    try {
                        Log.d("tag3", "Entered yet again!");
                        final JSONArray reviewsArray = new JSONArray(responseData);
                        for (int i = 0; i < reviewsArray.length(); i++) {
                            JSONArray reviewObject = reviewsArray.getJSONArray(i);
                            String imageBase64 = reviewObject.getString(1);
                            foodName = reviewObject.getString(3);
                            location = reviewObject.getString(2);
                            foodTitle = reviewObject.getString(5);
                            foodReview = reviewObject.getString(6);
                            foodRating = reviewObject.getString(4);

                            byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
                            bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            // Split the location string into latitude and longitude
                            String[] parts = location.split(",");
                            double latitude = Double.parseDouble(parts[0].trim());
                            double longitude = Double.parseDouble(parts[1].trim());
                            getAddressFromLocation(Activity_Myreview.this, latitude, longitude);

                            // Inflate your review item layout
                            final View view = getLayoutInflater().inflate(R.layout.myreviews, null);
                            ImageView image = view.findViewById(R.id.imageFood);
                            TextView nameTextView = view.findViewById(R.id.addedname);
                            TextView locationTextView = view.findViewById(R.id.foodlocation);
                            TextView titleTextView = view.findViewById(R.id.addedtitle);
                            TextView reviewTextView = view.findViewById(R.id.addedreview);
                            TextView ratingTextView = view.findViewById(R.id.score);

                            image.setImageBitmap(bitmap);
                            nameTextView.setText(foodName);
                            locationTextView.setText(addressLine);
                            titleTextView.setText(foodTitle);
                            reviewTextView.setText(foodReview);
                            ratingTextView.setText(foodRating);

                            // Add the inflated view to the ScrollView
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.addView(view);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Continue parsing the response...
                } else {
                    Log.e("Response", "Unsuccessful response: " + response.code());
                }
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
}
