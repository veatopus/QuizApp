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
    public static final int CORRECT_ANSWER = 1;
    public static final int CORRECT_ANSWER_AND_AND_FINAL_ANSWER = 11;
    public static final int WRONG_ANSWER = 2;
    public static final int WRONG_ANSWER_AND_AND_FINAL_ANSWER = 22;
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

    public int onButtonClick(int positionQuestion, int positionAnswer) {
        Log.e("ololo", "onButtonClick: hhhhhhhhhhhhhhhhh");
        int result = 0;
        QuizModel quizModel = Objects.requireNonNull(listQuestions.getValue()).get(positionQuestion);
        String userAnswer = quizModel.arrayAnswer[positionAnswer];
        if (answerAmount.getValue() != null) {
            if (userAnswer.equals(quizModel.correctAnswer)) {
                if (correctAnswerAmount + wrongAnswerAmount >= listQuestions.getValue().size() - 1)
                    result = CORRECT_ANSWER_AND_AND_FINAL_ANSWER;
                else {
                    correctAnswerAmount++;
                    result = CORRECT_ANSWER;
                }
            } else {
                if (correctAnswerAmount + wrongAnswerAmount >= listQuestions.getValue().size() - 1)
                    result = WRONG_ANSWER_AND_AND_FINAL_ANSWER;
                else {
                    wrongAnswerAmount++;
                    result = WRONG_ANSWER;
                }
            }
        }
        answerAmount.setValue(correctAnswerAmount + wrongAnswerAmount);
        return result;
    }
}