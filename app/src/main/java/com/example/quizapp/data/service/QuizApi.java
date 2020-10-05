package com.example.quizapp.data.service;

import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.TriviaCategories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {
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