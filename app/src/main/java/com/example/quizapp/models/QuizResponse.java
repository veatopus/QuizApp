package com.example.quizapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizResponse {

    @SerializedName("response_code")
    private Integer responseCode;
    @SerializedName("results")
    private List<Result> results = null;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
