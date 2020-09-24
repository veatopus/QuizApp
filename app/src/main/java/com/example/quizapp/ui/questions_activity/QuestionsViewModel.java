package com.example.quizapp.ui.questions_activity;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.R;
import com.example.quizapp.models.QuizModel;

import java.util.List;
import java.util.Objects;

public class QuestionsViewModel extends ViewModel {
    private QuestionsRepository questionsRepository;
    MutableLiveData<List<QuizModel>> listQuestions = new MutableLiveData<>();
    private int correctAnswerAmount = 0;
    private int wrongAnswerAmount = 0;
    MutableLiveData<Integer> answerAmount = new MutableLiveData<>();

    public QuestionsViewModel() {
        questionsRepository = new QuestionsRepository();
        answerAmount.setValue(0);
    }

    public void setAmountQuestions(int questionsAmount) {
        listQuestions.setValue(questionsRepository.getQuestions(questionsAmount));
    }

    public void onButtonClick(View view, int positionQuestion, int positionAnswer) {
        Log.e("ololo", "onButtonClick: hhhhhhhhhhhhhhhhh");

        QuizModel quizModel = Objects.requireNonNull(listQuestions.getValue()).get(positionQuestion);
        String userAnswer = quizModel.arrayAnswer[positionAnswer];
        if (answerAmount.getValue() != null) {
            if (userAnswer.equals(quizModel.correctAnswer)) {
                correctAnswerAmount++;
                view.setBackgroundResource(R.drawable.item_button_2);
            } else {
                wrongAnswerAmount++;
                view.setBackgroundResource(R.drawable.item_button_3);
            }
        }
        answerAmount.setValue(correctAnswerAmount + wrongAnswerAmount);

    }
}