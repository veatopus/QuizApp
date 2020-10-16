package com.example.quizapp.data.locally;

import com.example.quizapp.models.HistoryResultModel;

import java.util.List;

public interface IHistoryClient {
    void insertHistoryResult(HistoryResultModel resultModel);
    List<HistoryResultModel> getAllHistoryResult();
    void clearAll();
    void deleteToId(Long id);
}