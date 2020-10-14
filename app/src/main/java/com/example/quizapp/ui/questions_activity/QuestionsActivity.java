package com.example.quizapp.ui.questions_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.quizapp.R;
import com.example.quizapp.adapters.QuizAdapter;
import com.example.quizapp.databinding.ActivityQuestionsBinding;
import com.example.quizapp.interfaces.OnResultAnswerClickListener;
import com.example.quizapp.ui.result_activity.ResultActivity;

public class QuestionsActivity extends AppCompatActivity implements OnResultAnswerClickListener {
    public static final String RESULT_QUESTIONS_AMOUNT_KEY = "RESULT_QUESTIONS_AMOUNT_KEY";
    public static final String RESULT_CATEGORY_KEY = "RESULT_CATEGORY_KEY";
    public static final String RESULT_TITLE_KEY = "TITLE";
    public static final String RESULT_DIFFICULTY_KEY = "RESULT_DIFFICULTY_KEY";
    private int questionsAmount;
    private int category;
    private String title, difficulty = "Any type";
    private ActivityQuestionsBinding binding;
    private QuizAdapter quizAdapter;
    private QuestionsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_questions);

        init();
        setArg();
        observe();
    }

    private void setArg() {
        mViewModel.setAmountQuestions(questionsAmount, category, difficulty);
        binding.recyclerview.setAdapter(quizAdapter);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        binding.progressBarQuestionActivity.setMax(questionsAmount);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerview);
        quizAdapter.setAnswerClick(this);
        binding.progressBarQuestionActivity.setProgress(0);
        binding.categoryTitle.setText(title);
    }

    private void init() {
        mViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        binding.setViewModel(mViewModel);
        quizAdapter = new QuizAdapter();
        if (getIntent() != null) {
            questionsAmount = getIntent().getIntExtra(RESULT_QUESTIONS_AMOUNT_KEY, 10);
            category = getIntent().getIntExtra(RESULT_CATEGORY_KEY, 99);
            difficulty = getIntent().getStringExtra(RESULT_DIFFICULTY_KEY);
            title = getIntent().getStringExtra(RESULT_TITLE_KEY);
        }
    }

    private void observe() {
        mViewModel.listQuestions.observeForever(quizModels -> quizAdapter.setQuestions(quizModels));
        mViewModel.result.observeForever(resultQuiz -> {
            Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
            intent.putExtra(ResultActivity.RESULT_QUIZ_KEY, resultQuiz);
            startActivity(intent);
            finish();
        });
        mViewModel.questionPosition.observeForever(integer -> {
            binding.recyclerview.scrollToPosition(integer);
            binding.progressBarQuestionActivity.setProgress(integer);
        });
        mViewModel.isFinish.observeForever(aBoolean -> {
            if (aBoolean) super.finish();
        });
    }

    @Override
    public void onClick(int result, String answer) {
        mViewModel.onAnswerClick(result, title, difficulty, answer);
    }

    @Override
    public void onBackPressed() {
        if (mViewModel.questionPosition.getValue() != null) {
            mViewModel.questionPosition.setValue(mViewModel.questionPosition.getValue() - 1);
            if (mViewModel.questionPosition.getValue() < 0) super.onBackPressed();
        }

    }
}