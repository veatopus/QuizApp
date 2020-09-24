package com.example.quizapp.interfaces;

import android.view.View;

public interface OnButtonAnswerClick {
    void onClick(View view, int positionQuestion, int positionAnswer);
}