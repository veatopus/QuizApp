package com.example.quizapp.models;

import android.text.Html;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @SerializedName("category")
    private String category;
    @SerializedName("type")
    private String type;
    @SerializedName("difficulty")
    private String difficulty;
    @SerializedName("question")
    private String question;
    @SerializedName("correct_answer")
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers = null;
    private int userChoice;
    private boolean isChoice;
    MutableLiveData<Boolean> isSkipClicked = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> getIsSkipClicked() {
        return isSkipClicked;
    }

    public void setSkipClicked(boolean skipClicked) {
        isSkipClicked.setValue(skipClicked);
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public String getCategory() {
        return category;
    }

     public void setCategory(String category) {
        this.category = category;
    }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getIncorrectAnswers() {
        for (int i = 0; i < incorrectAnswers.size()-1; i++) {
            incorrectAnswers.set(i, Html.fromHtml(Html.fromHtml(incorrectAnswers.get(i)).toString()).toString());
        }
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

}