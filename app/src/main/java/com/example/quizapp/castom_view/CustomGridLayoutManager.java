package com.example.quizapp.castom_view;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomGridLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomGridLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollVertically();
    }
}