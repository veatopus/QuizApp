package com.example.quizapp.data.locally.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.models.HistoryResultModel;

import java.util.List;

@androidx.room.Dao
public interface HistoryDao {

    @Query("SELECT * FROM HistoryResultModel")
    List<HistoryResultModel> getAll();

    @Query("SELECT * FROM HistoryResultModel WHERE id = :id")
    HistoryResultModel getById(long id);

    @Insert
    long insert(HistoryResultModel historyResultModel);

    @Delete
    void delete(HistoryResultModel historyResultModel);

    @Query("DELETE FROM HistoryResultModel")
    void deleteAll();
}