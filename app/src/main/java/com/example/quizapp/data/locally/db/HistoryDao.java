package com.example.quizapp.data.locally.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.models.HistoryResultModel;

import java.util.List;

@androidx.room.Dao
public interface HistoryDao {

    @Query("SELECT * FROM HistoryResultModel")
    LiveData<List<HistoryResultModel>> getAll();

    @Query("SELECT * FROM HistoryResultModel WHERE id = :id")
    HistoryResultModel getById(long id);

    @Insert
    void insert(HistoryResultModel historyResultModel);

    @Query("DELETE FROM HistoryResultModel")
    void deleteAll();

    @Query("DELETE FROM historyresultmodel WHERE id = :id")
    void deleteToId(long id);
}