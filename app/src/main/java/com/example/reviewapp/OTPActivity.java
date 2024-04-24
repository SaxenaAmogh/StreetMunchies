package com.example.reviewapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OTPActivity extends AppCompatActivity {

    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp = getIntent().getStringExtra("otp");

        EditText et1 = findViewById(R.id.editText1);
        EditText et2 = findViewById(R.id.editText2);
        EditText et3 = findViewById(R.id.editText3);
        EditText et4 = findViewById(R.id.editText4);
        EditText et5 = findViewById(R.id.editText5);
        EditText et6 = findViewById(R.id.editText6);

        et1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et1.getText().toString().length()==1)     //size as per your requirement
                {
                    et2.requestFocus();
                    et2.setBackgroundResource(R.drawable.edit_text_bg2);
                    et1.setBackgroundResource(R.drawable.edit_text_bg);
                }

            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        et2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et2.getText().toString().length()==1)     //size as per your requirement
                {
                    et3.requestFocus();
                    et3.setBackgroundResource(R.drawable.edit_text_bg2);
                    et2.setBackgroundResource(R.drawable.edit_text_bg);
                }

            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        et3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et3.getText().toString().length()==1)     //size as per your requirement
                {
                    et4.requestFocus();
                    et4.setBackgroundResource(R.drawable.edit_text_bg2);
                    et3.setBackgroundResource(R.drawable.edit_text_bg);
                }

            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        et4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et4.getText().toString().length()==1)     //size as per your requirement
                {
                    et5.requestFocus();
                    et5.setBackgroundResource(R.drawable.edit_text_bg2);
                    et4.setBackgroundResource(R.drawable.edit_text_bg);
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        et5.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et5.getText().toString().length()==1)     //size as per your requirement
                {
                    et6.requestFocus();
                    et6.setBackgroundResource(R.drawable.edit_text_bg2);
                    et5.setBackgroundResource(R.drawable.edit_text_bg);
                }

            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
    }
    public void close(View view){
        onBackPressed();
    }
}
