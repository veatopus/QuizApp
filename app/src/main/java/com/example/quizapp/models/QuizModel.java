package com.example.quizapp.models;

public class QuizModel {
    public String title;
    public String[] arrayAnswer;
    public String correctAnswer;
    public TypeQuiz quiz;
    public int id;

    public QuizModel(String title, String[] arrayAnswer, String correctAnswer, TypeQuiz quiz) {
        this.title = title;
        this.arrayAnswer = arrayAnswer;
        this.correctAnswer = correctAnswer;
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getArrayAnswer() {
        return arrayAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public TypeQuiz getQuiz() {
        return quiz;
    }

    public enum TypeQuiz{YES_NO, OPTIONS}
}
