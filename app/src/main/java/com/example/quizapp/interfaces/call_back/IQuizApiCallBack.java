package com.example.quizapp.interfaces.call_back;

import com.example.quizapp.core.IBaseCollBack;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.TriviaCategories;

public interface IQuizApiCallBack {
    interface Questions extends IBaseCollBack<QuizResponse> {
        void onSuccess(QuizResponse quizResponse);

        void onFailure(Throwable throwable);
    }

    interface Categories extends IBaseCollBack<TriviaCategories> {
        void onSuccess(TriviaCategories categories);

        void onFailure(Throwable throwable);
    }
}