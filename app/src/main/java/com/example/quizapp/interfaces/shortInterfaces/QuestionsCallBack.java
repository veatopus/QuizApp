package com.example.quizapp.interfaces.shortInterfaces;

import com.example.quizapp.interfaces.call_back.IQuizApiCallBack;
import com.example.quizapp.models.QuizResponse;

public class QuestionsCallBack implements IQuizApiCallBack.Questions {
    @Override
    public void onSuccess(QuizResponse result) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
}