package com.example.quizapp.ui.questions_activity;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.quizapp.data.Repository;
import com.example.quizapp.data.service.QuizApiService;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.QuestionModel;
import com.example.quizapp.models.ResultQuiz;

import java.util.List;

public class QuestionsViewModel extends ViewModel implements QuizApiService.QuizApiCallBack.Questions {
    MutableLiveData<List<QuestionModel>> listQuestions = new MutableLiveData<>();
    MutableLiveData<ResultQuiz> result = new MutableLiveData<>();
    private int correctAnswerAmount = 0;
    private int wrongAnswerAmount = 0;

    public void setAmountQuestions(int questionsAmount, int category, String difficulty) {
        if (category == 99 && difficulty.equals("Any type")){
            Repository.getQuestions(questionsAmount, this);
        } else if (category == 99){
            Repository.getQuestionsWithDifficulty(questionsAmount, difficulty, this);
        } else if (difficulty.equals("Any type")){
            Repository.getQuestionsWithCategory(questionsAmount, category, this);
        } else {
            Repository.getQuestionsWithDifficultyAndCategory(questionsAmount, category, difficulty, this);
        }
    }

    public void onAnswerClick(boolean result) {
        if (result) correctAnswerAmount++;
        else wrongAnswerAmount++;
    }

    public void onLastAnswerClick(boolean result, String category, String difficulty) {
        if (result) correctAnswerAmount++;
        else wrongAnswerAmount++;

        this.result.setValue(new ResultQuiz(
                correctAnswerAmount>wrongAnswerAmount,
                category,
                difficulty,
                correctAnswerAmount + "/" + (correctAnswerAmount+wrongAnswerAmount),
                (correctAnswerAmount / (correctAnswerAmount + wrongAnswerAmount)) + "%"
        ));
    }

    @Override
    public void onSuccess(QuizResponse quizResponse) {
        listQuestions.setValue(quizResponse.getQuestionModels());
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("ololo", "onFailure: ", throwable);
    }
}