package com.example.quizapp.data;

import com.example.quizapp.data.locally.IHistoryClient;
import com.example.quizapp.data.service.IQuizApiClient;
import com.example.quizapp.interfaces.call_back.IQuizApiCallBack;
import com.example.quizapp.interfaces.shortInterfaces.QuestionsCallBack;
import com.example.quizapp.models.HistoryResultModel;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizResponse;

import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient, IHistoryClient {
    private final IHistoryClient historyStorage;
    private final IQuizApiClient quizApiClient;

    public QuizRepository(IHistoryClient historyStorage, IQuizApiClient quizApiClient) {
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

    @Override
    public void insertHistoryResult(HistoryResultModel resultModel) {
        historyStorage.insertHistoryResult(resultModel);
    }

    @Override
    public List<HistoryResultModel> getAllHistoryResult() {
        return historyStorage.getAllHistoryResult();
    }

    @Override
    public void clearAll() {
        historyStorage.clearAll();
    }

    @Override
    public void deleteToId(Long id) {
        historyStorage.deleteToId(id);
    }
}