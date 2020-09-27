package com.example.quizapp.data.service;

import androidx.annotation.NonNull;

import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.TriviaCategories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    QuizApi service = retrofit.create(QuizApi.class);

    public void getQuestionNoCategoryAndDifficulty(int amount, QuizApiCallBack.Questions callBack) {
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

    public void getQuestionsWithCategory(int amount, int category, QuizApiCallBack.Questions questions){
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

    public void getQuestionsWithDifficulty(int amount, String difficulty, QuizApiCallBack.Questions callBack){
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

    public void getQuestionsWithDifficultyAndCategory(int amount, int category, String difficulty, QuizApiCallBack.Questions callBack){
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

    public void getCategories(QuizApiCallBack.Categories callBack) {
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

    interface QuizApi {
        @GET("api.php")
        Call<QuizResponse> getQuestion(
                @Query("amount") int amount,
                @Query("category") int category,
                @Query("difficulty") String difficulty);

        @GET("api_category.php")
        Call<TriviaCategories> getCategories();

        @GET("api.php")
        Call<QuizResponse> getQuestionNoDifficulty(
                @Query("amount") int amount,
                @Query("category") int category);

        @GET("api.php")
        Call<QuizResponse> getQuestionNoCategory(
                @Query("amount") int amount,
                @Query("difficulty") String difficulty);

        @GET("api.php")
        Call<QuizResponse> getQuestionNoCategoryAndDifficulty(
                @Query("amount") int amount);
    }

    public interface QuizApiCallBack {
        interface Questions {
            void onSuccess(QuizResponse quizResponse);

            void onFailure(Throwable throwable);
        }

        interface Categories {
            void onSuccess(TriviaCategories categories);

            void onFailure(Throwable throwable);
        }
    }
}
