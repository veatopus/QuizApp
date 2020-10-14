package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.locally.HistoryStorage;
import com.example.quizapp.data.locally.IHistoryClient;
import com.example.quizapp.data.locally.db.AppDatabase;
import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.data.service.QuizApiService;

public class App extends Application {
    private static App instance;
    private QuizRepository quizRepository;
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        IQuizApiClient quizApiClient = new QuizApiService();
        IHistoryClient historyClient = new HistoryStorage();
        quizRepository = new QuizRepository(historyClient, quizApiClient);
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository() {
        return quizRepository;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}