package com.example.quizapp.ui.questions_activity;

import com.example.quizapp.data.service.QuizApiService;

public class QuestionsRepository {
    QuizApiService service = new QuizApiService();

    public void getQuestions(int amount, QuizApiService.QuizApiCallBack callBack){
        service.getQuestions(amount, callBack);
    }
}