package com.example.quizapp.ui.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.models.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    public MutableLiveData<List<HistoryModel>> listHistoryMutableLiveData = new MutableLiveData<>();

    public void updateData() {
        List<HistoryModel> historyModels = new ArrayList<>();
        historyModels.add(new HistoryModel("Any category", "hard", "8/10", "12 may 2019 20:32"));
        historyModels.add(new HistoryModel("Any category", "hard", "8/10", "12 may 2019 20:32"));
        historyModels.add(new HistoryModel("Any category", "hard", "8/10", "12 may 2019 20:32"));
        historyModels.add(new HistoryModel("Any category", "hard", "8/10", "12 may 2019 20:32"));
        historyModels.add(new HistoryModel("Any category", "hard", "8/10", "12 may 2019 20:32"));
        historyModels.add(new HistoryModel("Any category", "hard", "8/10", "12 may 2019 20:32"));
        listHistoryMutableLiveData.setValue(historyModels);
    }
}
