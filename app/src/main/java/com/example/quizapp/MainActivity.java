package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.quizapp.adapters.PagerAdapterMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapterMain pagerAdapterMain;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        viewPager = findViewById(R.id.main_view_pager);

        pagerAdapterMain = new PagerAdapterMain(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapterMain);
        viewPager.setOffscreenPageLimit(2);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });

    }
}