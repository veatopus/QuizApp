package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.locally.HistoryStorage;
import com.example.quizapp.data.locally.IHistoryStorage;
import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.data.service.QuizApiService;

public class App extends Application {
    private static App instance;
    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;
    private QuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        quizApiClient = new QuizApiService();
        historyStorage = new HistoryStorage();
        quizRepository = new QuizRepository(historyStorage, quizApiClient);
    }

    public static App getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository() {
        return quizRepository;
    }
}