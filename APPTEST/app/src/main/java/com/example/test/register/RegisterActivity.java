package com.example.test.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.test.R;
import com.example.test.home.HomeActivity;

public class RegisterActivity extends AppCompatActivity {
    ImageButton imageButtonBack;
    AppCompatButton btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        handle();
    }

    private void handle() {
        imageButtonBack.setOnClickListener(view ->{
            finish();
        });

        btnRegister.setOnClickListener(view ->{
            Intent intentHome = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intentHome);
        });
    }

    private void init() {
        imageButtonBack = findViewById(R.id.image_button_back);
        btnRegister = findViewById(R.id.btn_register);
    }
}