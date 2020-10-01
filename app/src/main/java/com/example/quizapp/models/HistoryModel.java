package com.example.quizapp.models;

public class HistoryModel {
    private String category;
    private String difficulty;
    private String correctAns;
    private String data;

    public HistoryModel(String category, String difficulty, String correctAns, String data) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.data = data;
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

    public String getData() {
        return data;
    }
}
