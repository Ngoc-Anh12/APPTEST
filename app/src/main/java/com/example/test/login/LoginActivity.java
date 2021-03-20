package com.example.test.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {
    ImageButton imageButtonBack;
    AppCompatButton btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        handle();
    }

    private void handle() {
        imageButtonBack.setOnClickListener(view ->{
            finish();
        });

        btnLogin.setOnClickListener(view ->{
            Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intentHome);
        });
    }


    private void init() {
        imageButtonBack = findViewById(R.id.image_button_back);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}