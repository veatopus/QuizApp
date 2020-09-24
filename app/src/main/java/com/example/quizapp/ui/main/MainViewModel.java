package com.example.quizapp.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();


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
}