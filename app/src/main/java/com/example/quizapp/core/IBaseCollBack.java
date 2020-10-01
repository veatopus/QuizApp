package com.example.quizapp.core;

public interface IBaseCollBack<T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}