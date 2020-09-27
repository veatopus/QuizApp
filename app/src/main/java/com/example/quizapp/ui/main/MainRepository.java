package com.example.quizapp.ui.main;

import com.example.quizapp.App;
import com.example.quizapp.data.service.QuizApiService;

public class MainRepository {

    void getCategory(QuizApiService.QuizApiCallBack.Categories callBack){
        App.getInstance().getService().getCategories(callBack);
    }
}