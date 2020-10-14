package com.example.quizapp.ui.result_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryResultModel;
import com.example.quizapp.models.ResultQuiz;

public class ResultViewModel extends ViewModel {
    MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);

    public void onFinishClicked(){
        isFinished.setValue(true);
    }

    public void initResult(ResultQuiz resultQuiz) {
        App.getInstance().getQuizRepository().insertHistoryResult(new HistoryResultModel(
                resultQuiz.getCategory(),
                resultQuiz.getDifficulty(),
                resultQuiz.getAmountCorrectAns(),
                ""/*TODO: get date*/,
                resultQuiz.getAnswers()
        ));
    }

}