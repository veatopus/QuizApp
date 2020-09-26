package com.example.quizapp.data.service;

import com.example.quizapp.models.QuizResponse;

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

    public void getQuestions(int amount, QuizApiCallBack callBack){
        service.getQuestion(amount, 9, "medium")
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<QuizResponse> call, Throwable t) {
                        callBack.onFailure(t);
                    }
                });
    }

    interface QuizApi{
        @GET("api.php")
        Call<QuizResponse> getQuestion(
                @Query("amount") int amount,
                @Query("category") int category,
                @Query("difficulty") String difficulty
        );
    }

    public interface QuizApiCallBack{
        void onSuccess(QuizResponse quizResponse);
        void onFailure(Throwable throwable);
    }
}
