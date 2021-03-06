package com.example.quizapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryResultModel;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    public LiveData<List<HistoryResultModel>> listHistoryMutableLiveData = new MutableLiveData<>();

    public HistoryViewModel() {
        main();
    }

    private void main() {
        listHistoryMutableLiveData = App.getInstance().getQuizRepository().getAllHistoryResult();
    }

    public void popupMenuDelete(int position) {
        App.getInstance().getQuizRepository().deleteToId(listHistoryMutableLiveData.getValue().get(position).getId());
    }
}