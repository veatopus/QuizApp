package com.example.quizapp.data.service;

import com.example.quizapp.interfaces.call_back.IQuizApiCallBack;

public interface IQuizApiClient {
    void getQuestionNoCategoryAndDifficulty(int amount, IQuizApiCallBack.Questions callBack);

    void getQuestionsWithCategory(int amount, int category, IQuizApiCallBack.Questions questions);

    void getQuestionsWithDifficulty(int amount, String difficulty, IQuizApiCallBack.Questions callBack);

    void getQuestionsWithDifficultyAndCategory(int amount, int category, String difficulty, IQuizApiCallBack.Questions callBack);

    void getCategories(IQuizApiCallBack.Categories callBack);

    void getQuestions(int questionsAmount, IQuizApiCallBack.Questions callBack);
}