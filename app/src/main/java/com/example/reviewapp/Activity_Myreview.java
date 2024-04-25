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
import android.widget.ImageView;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_Myreview extends AppCompatActivity {
    String location, addressLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myreviews);

        ImageView image = findViewById(R.id.imageFood);
        TextView name = findViewById(R.id.addedname);
        TextView locate = findViewById(R.id.foodlocation);
        TextView title = findViewById(R.id.addedtitle);
        TextView review = findViewById(R.id.addedreview);
        TextView rating = findViewById(R.id.score);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.211.238:5000/get_review").build();

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
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        String imageBase64 = jsonObject.getString("image");
                        String foodname = jsonObject.getString("foodname");
                        String foodtitle = jsonObject.getString("title");
                        String foodreview = jsonObject.getString("review");
                        String foodrating = jsonObject.getString("rating");

                        location = jsonObject.getString("location");

                        String[] parts = location.split(",");
                        double latitude = Double.parseDouble(parts[0].trim());
                        double longitude = Double.parseDouble(parts[1].trim());

                        byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        runOnUiThread(() -> {
                            image.setImageBitmap(bitmap);
                            name.setText(foodname);
                            title.setText(foodtitle);
                            review.setText(foodreview);
                            rating.setText(foodrating);
                            getAddressFromLocation(Activity_Myreview.this, latitude, longitude);
                            locate.setText((addressLine));
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
