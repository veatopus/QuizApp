package com.example.quizapp.data.service;

import androidx.annotation.NonNull;

import com.example.quizapp.interfaces.call_back.IQuizApiCallBack;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.TriviaCategories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiService implements IQuizApiClient {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private QuizApi service = retrofit.create(QuizApi.class);

    @Override
    public void getQuestionNoCategoryAndDifficulty(int amount, IQuizApiCallBack.Questions callBack) {
        service.getQuestionNoCategoryAndDifficulty(amount)
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {
                        callBack.onFailure(t);
                    }
                });
    }

    @Override
    public void getQuestionsWithCategory(int amount, int category, IQuizApiCallBack.Questions questions) {
        service.getQuestionNoDifficulty(amount, category)
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                        questions.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {
                        questions.onFailure(t);
                    }
                });
    }

    @Override
    public void getQuestionsWithDifficulty(int amount, String difficulty, IQuizApiCallBack.Questions callBack) {
        service.getQuestionNoCategory(amount, difficulty)
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {
                        callBack.onFailure(t);
                    }
                });
    }

    @Override
    public void getQuestionsWithDifficultyAndCategory(int amount, int category, String difficulty, IQuizApiCallBack.Questions callBack) {
        service.getQuestion(amount, category, difficulty)
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {
                        callBack.onFailure(t);
                    }
                });
    }

    @Override
    public void getCategories(IQuizApiCallBack.Categories callBack) {
        service.getCategories()
                .enqueue(new Callback<TriviaCategories>() {
                    @Override
                    public void onResponse(@NonNull Call<TriviaCategories> call, @NonNull Response<TriviaCategories> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<TriviaCategories> call, @NonNull Throwable t) {
                        callBack.onFailure(t);
                    }
                });
    }

    @Override
    public void getQuestions(int questionsAmount, IQuizApiCallBack.Questions callBack) {
        service.getQuestionNoCategoryAndDifficulty(questionsAmount)
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                        service.getQuestionNoCategoryAndDifficulty(questionsAmount)
                                .enqueue(new Callback<QuizResponse>() {
                                    @Override
                                    public void onResponse(@NonNull Call<QuizResponse> call, @NonNull Response<QuizResponse> response) {
                                        callBack.onSuccess(response.body());
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {
                                        callBack.onFailure(t);
                                    }
                                });
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuizResponse> call, @NonNull Throwable t) {

                    }
                });
    }

}