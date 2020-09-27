package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.service.QuizApiService;

public class App extends Application {
    private static App instance;
    private QuizApiService service;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        service = new QuizApiService();
    }

    public static App getInstance() {
        return instance;
    }

    public QuizApiService getService() {
        return service;
    }
}
