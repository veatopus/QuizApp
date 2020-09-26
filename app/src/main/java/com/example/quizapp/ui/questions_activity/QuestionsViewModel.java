package com.example.quizapp.ui.questions_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.quizapp.models.QuizModel;

import java.util.List;

public class QuestionsViewModel extends ViewModel {
    private QuestionsRepository questionsRepository;
    MutableLiveData<List<QuizModel>> listQuestions = new MutableLiveData<>();
    private int correctAnswerAmount = 0;
    private int wrongAnswerAmount = 0;

    public QuestionsViewModel() {
        questionsRepository = new QuestionsRepository();
    }

    public void setAmountQuestions(int questionsAmount) {
        listQuestions.setValue(questionsRepository.getQuestions(questionsAmount));
    }

    public void onAnswerClick(boolean result) {
        if (result) correctAnswerAmount++;
        else wrongAnswerAmount++;
    }
}