package com.example.quizapp.models;

import java.io.Serializable;

public class ResultQuiz implements Serializable {
    private boolean isWin;
    private String category;
    private String difficulty;
    private String correctAns;
    private String resultPercentage;

    public ResultQuiz(boolean isWin, String category, String difficulty, String correctAns, double resultPercentage) {
        this.isWin = isWin;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.resultPercentage = (int) resultPercentage + "%";
    }

    public boolean isWin() {
        return isWin;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public String getResultPercentage() {
        return resultPercentage;
    }
}