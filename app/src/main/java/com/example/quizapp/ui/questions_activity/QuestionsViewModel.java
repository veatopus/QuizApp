package com.example.quizapp.ui.questions_activity;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.quizapp.data.service.QuizApiService;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.Result;

import java.util.List;

public class QuestionsViewModel extends ViewModel implements QuizApiService.QuizApiCallBack {
    private QuestionsRepository questionsRepository;
    MutableLiveData<List<Result>> listQuestions = new MutableLiveData<>();
    private int correctAnswerAmount = 0;
    private int wrongAnswerAmount = 0;

    public QuestionsViewModel() {
        questionsRepository = new QuestionsRepository();
    }

    public void setAmountQuestions(int questionsAmount) {
        questionsRepository.getQuestions(questionsAmount, this);
    }

    public void onAnswerClick(boolean result) {
        if (result) correctAnswerAmount++;
        else wrongAnswerAmount++;
    }

    @Override
    public void onSuccess(QuizResponse quizResponse) {
        listQuestions.setValue(quizResponse.getResults());
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("ololo", "onFailure: ", throwable);

    }
}