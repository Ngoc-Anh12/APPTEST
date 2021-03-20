package com.example.test;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.login.LoginActivity;
import com.example.test.login.ViewPagerAdapter;
import com.example.test.register.RegisterActivity;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    AppCompatButton btnLogin, btnRegister;
    int NUM_PAGES = 2;
    Timer timer;
    final long DELAY_MS = 3000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    CircleIndicator layoutDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        viewPage();

        handle();
    }

    private void viewPage() {
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%NUM_PAGES);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, DELAY_MS,PERIOD_MS);
        layoutDots.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        layoutDots = findViewById(R.id.layout_dots);
    }

    private void handle() {
        btnLogin.setOnClickListener(view ->{
            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intentLogin);
        });
        btnRegister.setOnClickListener(view ->{
            Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intentRegister);
        });
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}