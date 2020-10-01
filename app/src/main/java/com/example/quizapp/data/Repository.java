package com.example.quizapp.data;

import com.example.quizapp.App;
import com.example.quizapp.data.service.QuizApiService;

public class Repository {

    static public void getCategory(QuizApiService.QuizApiCallBack.Categories callBack){
        App.getInstance().getService().getCategories(callBack);
    }

    static public void getQuestions(int amount, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionNoCategoryAndDifficulty(amount, callBack);
    }

    static public void getQuestionsWithDifficultyAndCategory(int amount, int category, String difficulty, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithDifficultyAndCategory(amount, category, difficulty, callBack);
    }

    static public void getQuestionsWithDifficulty(int amount, String difficulty, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithDifficulty(amount, difficulty, callBack);
    }

    static public void getQuestionsWithCategory(int amount, int category, QuizApiService.QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithCategory(amount, category, callBack);
    }
}