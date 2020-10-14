package com.example.quizapp.models;

import java.io.Serializable;

public class ResultQuiz implements Serializable {
    private final boolean isWin;
    private final String category;
    private final String difficulty;
    private final String correctAns;
    private final String resultPercentage;
    private final int AmountCorrectAns;
    private final Answers answers;

    public ResultQuiz(boolean isWin, String category, String difficulty, String correctAns, double resultPercentage, Answers answers, int AmountCorrectAns) {
        this.isWin = isWin;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.resultPercentage = (int) resultPercentage + "%";
        this.answers = answers;
        this.AmountCorrectAns = AmountCorrectAns;
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

    public Answers getAnswers() {
        return answers;
    }

    public int getAmountCorrectAns() {
        return AmountCorrectAns;
    }
}