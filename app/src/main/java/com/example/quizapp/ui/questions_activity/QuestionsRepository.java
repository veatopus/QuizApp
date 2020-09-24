package com.example.quizapp.ui.questions_activity;

import com.example.quizapp.data.locally.QuestionsBD;
import com.example.quizapp.models.QuizModel;

import java.util.List;

public class QuestionsRepository {
    private QuestionsBD questionsBD = new QuestionsBD();

    public List<QuizModel> getQuestions(){
        return questionsBD.getAll();
    }

    public List<QuizModel> getQuestions(int amount){
        return questionsBD.getQuestions(amount);
    }
}