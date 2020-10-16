package com.example.quizapp.ui.result_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryResultModel;
import com.example.quizapp.models.ResultQuiz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ResultViewModel extends ViewModel {
    MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);

    public void onFinishClicked(){
        isFinished.setValue(true);
    }

    public void initResult(ResultQuiz resultQuiz) {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        App.getInstance().getQuizRepository().insertHistoryResult(new HistoryResultModel(
                resultQuiz.getCategory(),
                resultQuiz.getDifficulty(),
                resultQuiz.getAmountCorrectAns(),
                formattedDate,
                resultQuiz.getAnswers()
        ));
    }

}