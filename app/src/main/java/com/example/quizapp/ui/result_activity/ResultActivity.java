package com.example.quizapp.ui.result_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.models.ResultQuiz;

public class ResultActivity extends AppCompatActivity {

    private ResultQuiz resultQuiz;
    private ActivityResultBinding binding;

    public static final String RESULT_QUIZ_KEY = "RESULT_QUIZ_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        setContentView(R.layout.activity_result);
        Log.e("ololo", "ResultActivity: onCreate: ");
        if (getIntent() != null) getArg(getIntent());
    }

    private void getArg(Intent data) {
        resultQuiz = (ResultQuiz) data.getSerializableExtra(RESULT_QUIZ_KEY);
        binding.setModel(resultQuiz);
        binding.correctAns.setText(resultQuiz.getCategory());
        Log.e("ololo", "getArg: " + binding.getModel().getCategory());
    }
}