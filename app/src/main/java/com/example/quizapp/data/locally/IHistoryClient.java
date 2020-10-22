package com.example.quizapp.data.locally;

import androidx.lifecycle.LiveData;

import com.example.quizapp.models.HistoryResultModel;

import java.util.List;

public interface IHistoryClient {
    void insertHistoryResult(HistoryResultModel resultModel);
    LiveData<List<HistoryResultModel>> getAllHistoryResult();
    void clearAll();
    void deleteToId(Long id);
}
