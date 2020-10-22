package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.example.quizapp.adapters.PagerAdapterMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapterMain adapter;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);


        viewPager = findViewById(R.id.main_view_pager);
        adapter = new PagerAdapterMain(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_main:
                    viewPager.setCurrentItem(0, false);
                    setTitle("Quiz");
                    break;
                case R.id.menu_history:
                    viewPager.setCurrentItem(1, false);
                    setTitle("History");
                    break;
                case R.id.menu_setting:
                    viewPager.setCurrentItem(2, false);
                    setTitle("Settings");
                    break;
            }
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setTheme(App.getInstance().getPrefs().getTheme());
    }
}