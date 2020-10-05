package com.example.quizapp.ui.result_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.models.ResultQuiz;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_QUIZ_KEY = "RESULT_QUIZ_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        ActivityResultBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_result);
        if (getIntent() != null)
            binding.setModel((ResultQuiz) getIntent().getSerializableExtra(RESULT_QUIZ_KEY));
        binding.btnFinish.setOnClickListener(v -> finish());
    }
}