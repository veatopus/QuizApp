package com.example.quizapp.data;

import com.example.quizapp.data.locally.IHistoryStorage;
import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.interfaces.call_back.IQuizApiCallBack;
import com.example.quizapp.interfaces.shortInterfaces.QuestionsCallBack;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.ui.questions_activity.QuestionsViewModel;

import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {
    IHistoryStorage historyStorage;
    IQuizApiClient quizApiClient;

    public QuizRepository(IHistoryStorage historyStorage, IQuizApiClient quizApiClient) {
        this.historyStorage = historyStorage;
        this.quizApiClient = quizApiClient;
    }

    @Override
    public void getQuestionNoCategoryAndDifficulty(int amount, IQuizApiCallBack.Questions callBack) {
        quizApiClient.getQuestionNoCategoryAndDifficulty(amount,
                new QuestionsCallBack() {
                    @Override
                    public void onSuccess(QuizResponse result) {
                        addCorrectAnsInIncorrect(result.getQuestions());
                        callBack.onSuccess(result);
                    }
                });
    }

    @Override
    public void getQuestionsWithCategory(int amount, int category, IQuizApiCallBack.Questions questions) {
        quizApiClient.getQuestionsWithCategory(amount,
                category,
                new QuestionsCallBack() {
                    @Override
                    public void onSuccess(QuizResponse result) {
                        addCorrectAnsInIncorrect(result.getQuestions());
                        questions.onSuccess(result);
                    }
                });
    }

    @Override
    public void getQuestionsWithDifficulty(int amount, String difficulty, IQuizApiCallBack.Questions callBack) {
        quizApiClient.getQuestionsWithDifficulty(amount,
                difficulty,
                new QuestionsCallBack() {
                    @Override
                    public void onSuccess(QuizResponse result) {
                        addCorrectAnsInIncorrect(result.getQuestions());
                        callBack.onSuccess(result);
                    }
                });
    }

    @Override
    public void getQuestionsWithDifficultyAndCategory(int amount, int category, String difficulty, IQuizApiCallBack.Questions callBack) {
        quizApiClient.getQuestionsWithDifficultyAndCategory(amount,
                category,
                difficulty,
                new QuestionsCallBack() {
                    @Override
                    public void onSuccess(QuizResponse result) {
                        addCorrectAnsInIncorrect(result.getQuestions());
                        callBack.onSuccess(result);
                    }
                });
    }

    @Override
    public void getCategories(IQuizApiCallBack.Categories callBack) {
        quizApiClient.getCategories(callBack);
    }

    @Override
    public void getQuestions(int questionsAmount, IQuizApiCallBack.Questions callBack) {
        quizApiClient.getQuestions(questionsAmount, new QuestionsCallBack(){
            @Override
            public void onSuccess(QuizResponse result) {
                addCorrectAnsInIncorrect(result.getQuestions());
                callBack.onSuccess(result);
            }
        });
    }

    private void addCorrectAnsInIncorrect(List<Question> questions) {
        for (Question question : questions) {
            question.getIncorrectAnswers().add(question.getCorrectAnswer());
            Collections.shuffle(question.getIncorrectAnswers());
        }
    }
}