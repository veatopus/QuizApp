package com.example.quizapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private final SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        sharedPreferences = context.getSharedPreferences("themeSharedPreferences",Context.MODE_PRIVATE);
    }

    public void isTheme(int value) {
        sharedPreferences
                .edit()
                .putInt("theme", value)
                .apply();
    }

    public int getTheme(){
        return sharedPreferences.getInt("theme", 0);
    }
}
