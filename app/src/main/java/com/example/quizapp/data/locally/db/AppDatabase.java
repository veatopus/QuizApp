package com.example.quizapp.data.locally.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizapp.models.HistoryResultModel;

@Database(entities = {HistoryResultModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryDao historyResultDao();
}
