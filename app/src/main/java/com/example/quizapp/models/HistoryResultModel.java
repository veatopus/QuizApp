package com.example.quizapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.quizapp.converters.HistoryConverter;

import java.util.List;

@Entity
public class HistoryResultModel {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "correctAns")
    private  int correctAns;
    @ColumnInfo(name = "category")
    private  String category;
    @ColumnInfo(name = "difficulty")
    private  String difficulty;
    @ColumnInfo(name = "date")
    private  String date;
    @TypeConverters(HistoryConverter.class)
    @ColumnInfo(name = "answers")
    private Answers answers;

    public HistoryResultModel(String category, String difficulty, int correctAns, String date, Answers answers) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.date = date;
        this.answers = answers;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public String getDate() {
        return date;
    }

    public Answers getQuestions() {
        return answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public Answers getAnswers() {
        return answers;
    }
}