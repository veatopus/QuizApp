package com.example.quizapp.models;

import com.example.quizapp.interfaces.OnSettingItemClickListener;

public class SettingItemModel {
    private int colorTitle;
    private String title;
    private OnSettingItemClickListener onSettingItemClickListener;

    public SettingItemModel(String title, OnSettingItemClickListener onSettingItemClickListener) {
        this.title = title;
        this.onSettingItemClickListener = onSettingItemClickListener;
    }

    public SettingItemModel(int colorTitle, String title, OnSettingItemClickListener onSettingItemClickListener) {
        this.colorTitle = colorTitle;
        this.title = title;
        this.onSettingItemClickListener = onSettingItemClickListener;
    }

    public void setOnSettingItemClickListener(OnSettingItemClickListener onSettingItemClickListener) {
        this.onSettingItemClickListener = onSettingItemClickListener;
    }

    public OnSettingItemClickListener getOnSettingItemClickListener() {
        return onSettingItemClickListener;
    }

    public int getColorTitle() {
        return colorTitle;
    }

    public String getTitle() {
        return title;
    }
}