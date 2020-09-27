package com.example.quizapp.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.data.service.QuizApiService;
import com.example.quizapp.models.TriviaCategories;

public class MainViewModel extends ViewModel implements QuizApiService.QuizApiCallBack.Categories {
    MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();
    MutableLiveData<TriviaCategories> triviaCategories = new MutableLiveData<>();
    private MainRepository repository = new MainRepository();

    public MainViewModel() {
        repository.getCategory(this);
    }

    public void buttonPlusCLicked() {
        if (progressBarSuccess.getValue() == null){
            progressBarSuccess.setValue(10);
        }
        progressBarSuccess.setValue(progressBarSuccess.getValue()+1);
    }

    public void buttonMinusCLicked() {
        if (progressBarSuccess.getValue() == null){
            progressBarSuccess.setValue(10);
        }
        progressBarSuccess.setValue(progressBarSuccess.getValue()-1);
    }

    @Override
    public void onSuccess(TriviaCategories categories) {
        triviaCategories.setValue(categories);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("ololo", "onFailure: ", throwable);
    }
}