package com.example.quizapp.ui.questions_activity;

import com.example.quizapp.App;
import com.example.quizapp.data.service.QuizApiService;

public class QuestionsRepository {

    public void getQuestions(int amount, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionNoCategoryAndDifficulty(amount, callBack);
    }

    public void getQuestionsWithDifficultyAndCategory(int amount, int category, String difficulty, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithDifficultyAndCategory(amount, category, difficulty, callBack);
    }

    public void getQuestionsWithDifficulty(int amount, String difficulty, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithDifficulty(amount, difficulty, callBack);
    }

    public void getQuestionsWithCategory(int amount, int category, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithCategory(amount, category, callBack);
    }
}