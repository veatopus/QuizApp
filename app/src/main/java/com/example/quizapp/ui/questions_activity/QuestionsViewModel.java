package com.example.quizapp.ui.questions_activity;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.quizapp.App;
import com.example.quizapp.interfaces.call_back.IQuizApiCallBack;
import com.example.quizapp.interfaces.shortInterfaces.CountDownTimer;
import com.example.quizapp.models.Answers;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.ResultQuiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.quizapp.adapters.QuizAdapter.ViewHolder.CORRECT_ANSWER;
import static com.example.quizapp.adapters.QuizAdapter.ViewHolder.CORRECT_ANSWER_AND_FINAL_ANSWER;
import static com.example.quizapp.adapters.QuizAdapter.ViewHolder.WRONG_ANSWER_AND_FINAL_ANSWER;

public class QuestionsViewModel extends ViewModel implements IQuizApiCallBack.Questions {
    MutableLiveData<List<Question>> listQuestions = new MutableLiveData<>();
    MutableLiveData<ResultQuiz> result = new MutableLiveData<>();
    MutableLiveData<Integer> questionPosition = new MutableLiveData<>(0);
    MutableLiveData<Boolean> isFinish = new MutableLiveData<>(false);
    private int correctAnswerAmount = 0;
    private List<String> answers = new ArrayList<>();

    public void setAmountQuestions(int questionsAmount, int category, String difficulty) {
        if (category == 99 && difficulty.equals("Any type")) {
            App.getInstance().getQuizRepository().getQuestions(questionsAmount, this);
        } else if (category == 99) {
            App.getInstance().getQuizRepository().getQuestionsWithDifficulty(questionsAmount, difficulty, this);
        } else if (difficulty.equals("Any type")) {
            App.getInstance().getQuizRepository().getQuestionsWithCategory(questionsAmount, category, this);
        } else {
            App.getInstance().getQuizRepository().getQuestionsWithDifficultyAndCategory(questionsAmount, category, difficulty, this);
        }
    }

    public void onAnswerClick(int result, String category, String difficulty, String answer) {
        answers.add(answer);
        Log.e("ololo", "onAnswerClick: ");
        if (result == CORRECT_ANSWER_AND_FINAL_ANSWER) {
            correctAnswerAmount++;
            onLastAnswerClick(category, difficulty);
        } else if (result == CORRECT_ANSWER)
            correctAnswerAmount++;
        else if (result == WRONG_ANSWER_AND_FINAL_ANSWER)
            onLastAnswerClick(category, difficulty);


        new CountDownTimer(500, 500) {
            @Override
            public void onFinish() {
                if (questionPosition.getValue() != null)
                    questionPosition.setValue(questionPosition.getValue() + 1);
            }
        }.start();
    }

    public void onLastAnswerClick(String category, String difficulty) {
        Log.e("ololo", "onLastAnswerClick: ");
        int questionAmount = Objects.requireNonNull(listQuestions.getValue()).size();
        this.result.setValue(new ResultQuiz(
                correctAnswerAmount > (questionAmount / 2),
                category,
                difficulty,
                correctAnswerAmount + "/" + questionAmount,
                (((double)correctAnswerAmount) / ((double)questionAmount)) * 100,
                new Answers(answers),
                correctAnswerAmount
        ));
    }

    @Override
    public void onSuccess(QuizResponse quizResponse) {
        listQuestions.setValue(quizResponse.getQuestions());
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("ololo", "onFailure: ", throwable);
    }

    public void onSkipClicked() {
        if (questionPosition.getValue() != null) {
            if (questionPosition.getValue() + 1 == listQuestions.getValue().size()){
                onLastAnswerClick("", "");
            }
            Objects.requireNonNull(listQuestions.getValue()).get(questionPosition.getValue()).getIsSkipClicked().setValue(true);
            questionPosition.setValue(questionPosition.getValue() + 1);
        }
    }

    public void onBackClicked() {
        if (questionPosition.getValue() != null) {
            if (questionPosition.getValue() < 0) isFinish.setValue(true);
            questionPosition.setValue(questionPosition.getValue() - 1);
        }
    }
}