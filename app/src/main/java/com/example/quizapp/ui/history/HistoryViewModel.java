package com.example.quizapp.ui.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryModel;
import com.example.quizapp.models.HistoryResultModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    public MutableLiveData<List<HistoryResultModel>> listHistoryMutableLiveData = new MutableLiveData<>();

    public void updateData() {
        listHistoryMutableLiveData.setValue(App.getInstance().getQuizRepository().getAllHistoryResult());
    }

    public void popupMenuDelete(int position) {
        App.getInstance().getQuizRepository().deleteToId(listHistoryMutableLiveData.getValue().get(position).getId());
    }
}