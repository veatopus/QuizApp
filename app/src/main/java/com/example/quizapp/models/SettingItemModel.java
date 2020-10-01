package com.example.quizapp.models;

public class SettingItemModel {
    private int colorTitle;
    private String title;

    public SettingItemModel(int colorTitle, String title) {
        this.colorTitle = colorTitle;
        this.title = title;
    }

    public int getColorTitle() {
        return colorTitle;
    }

    public String getTitle() {
        return title;
    }
}