package com.example.quizapp.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.interfaces.call_back.IQuizApiCallBack;
import com.example.quizapp.models.TriviaCategories;
import com.example.quizapp.models.TriviaCategory;

public class MainViewModel extends ViewModel implements IQuizApiCallBack.Categories {
    MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();
    MutableLiveData<TriviaCategories> triviaCategories = new MutableLiveData<>();
    MutableLiveData<Boolean> isStart = new MutableLiveData<>(false);
    MutableLiveData<Boolean> isShowDialogNoInternet = new MutableLiveData<>(false);
    private boolean isDoesHaveInternet = false;

    public void setDoesHaveInternet(boolean doesHaveInternet) {
        isDoesHaveInternet = doesHaveInternet;
    }

    public void updateCategory() {
        if (isDoesHaveInternet)
            App.getInstance().getQuizRepository().getCategories(this);
        else isShowDialogNoInternet.setValue(true);
    }

    public void buttonPlusCLicked() {
        if (progressBarSuccess.getValue() == null) {
            progressBarSuccess.setValue(10);
        } else {
            if (progressBarSuccess.getValue() < 20)
            progressBarSuccess.setValue(progressBarSuccess.getValue() + 1);
        }
    }

    public void buttonMinusCLicked() {
        if (progressBarSuccess.getValue() == null) {
            progressBarSuccess.setValue(10);
        } else {
            if (progressBarSuccess.getValue() > 0)
            progressBarSuccess.setValue(progressBarSuccess.getValue() - 1);
        }
    }

    @Override
    public void onSuccess(TriviaCategories categories) {
        TriviaCategory defCategory = new TriviaCategory();
        defCategory.setName("Any category");
        defCategory.setId(99);
        categories.getTriviaCategories().add(0, defCategory);
        triviaCategories.setValue(categories);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("ololo", "onFailure: ", throwable);
    }

    public void onButtonStartClick() {
        if (isDoesHaveInternet) isStart.setValue(true);
        else isShowDialogNoInternet.setValue(true);
    }
}