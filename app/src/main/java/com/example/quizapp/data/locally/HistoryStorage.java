package com.example.quizapp.data.locally;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryResultModel;

import java.util.List;

public class HistoryStorage implements IHistoryClient{

    @Override
    public void insertHistoryResult(HistoryResultModel resultModel) {
        App.getInstance().getAppDatabase().historyResultDao().insert(resultModel);
    }

    @Override
    public List<HistoryResultModel> getAllHistoryResult() {
        return App.getInstance().getAppDatabase().historyResultDao().getAll();
    }

    @Override
    public void clearAll() {
        App.getInstance().getAppDatabase().historyResultDao().deleteAll();
    }
}
