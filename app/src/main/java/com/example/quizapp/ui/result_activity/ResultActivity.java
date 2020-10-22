package com.example.quizapp.ui.result_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.models.ResultQuiz;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_QUIZ_KEY = "RESULT_QUIZ_KEY";
    private ResultViewModel resultViewModel;
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);

        init();
        setArg();
        observe();

    }

    private void observe() {
        resultViewModel.isFinished.observe(this, this::onFinish);
    }

    private void setArg() {
        binding.setViewModel(resultViewModel);
        if (getIntent() != null) {
            ResultQuiz resultQuiz = (ResultQuiz) getIntent().getSerializableExtra(RESULT_QUIZ_KEY);
            binding.setModel(resultQuiz);
            resultViewModel.initResult(resultQuiz);
        }
    }

    private void init() {
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
    }

    private void onFinish(Boolean isFinish) {
        if (isFinish) finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setTheme(App.getInstance().getPrefs().getTheme());
    }
}