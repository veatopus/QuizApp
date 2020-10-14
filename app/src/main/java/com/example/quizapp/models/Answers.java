package com.example.quizapp.models;

import java.io.Serializable;
import java.util.List;

public class Answers implements Serializable {
    List<String> answers;

    public Answers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
