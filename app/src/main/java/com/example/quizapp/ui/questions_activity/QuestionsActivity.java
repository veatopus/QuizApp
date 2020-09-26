package com.example.quizapp.ui.questions_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.quizapp.R;
import com.example.quizapp.adapters.QuizAdapter;
import com.example.quizapp.castom_view.CustomGridLayoutManager;
import com.example.quizapp.databinding.ActivityQuestionsBinding;
import com.example.quizapp.interfaces.ResultAnswerClickListener;

import static com.example.quizapp.adapters.QuizAdapter.ViewHolder.CORRECT_ANSWER;

public class QuestionsActivity extends AppCompatActivity implements ResultAnswerClickListener {
    public static final String RESULT_QUESTIONS_AMOUNT_KEY = "RESULT_QUESTIONS_AMOUNT_KEY";
    private int questionsAmount;
    private ActivityQuestionsBinding activityQuestionsBinding;
    private QuizAdapter quizAdapter;
    private QuestionsViewModel mViewModel;
    private CustomGridLayoutManager customGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        init();
        setArg();
        observeForever();
        setListener();
    }

    private void setListener() {
        activityQuestionsBinding.path.setOnClickListener(v -> finish());
        activityQuestionsBinding.skip.setOnClickListener(v -> scroll());
    }

    private void setArg() {
        mViewModel.setAmountQuestions(questionsAmount);
        activityQuestionsBinding.recyclerview.setAdapter(quizAdapter);
        activityQuestionsBinding.recyclerview.setLayoutManager(customGridLayoutManager);
        customGridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        activityQuestionsBinding.progressBarQuestionActivity.setMax(questionsAmount);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(activityQuestionsBinding.recyclerview);
        quizAdapter.setAnswerClick(this);
        activityQuestionsBinding.progressBarQuestionActivity.setProgress(0);
    }

    private void init() {
        mViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        activityQuestionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_questions);
        quizAdapter = new QuizAdapter();
        if (getIntent() != null)
            questionsAmount = getIntent().getIntExtra(RESULT_QUESTIONS_AMOUNT_KEY, 10);
        customGridLayoutManager = new CustomGridLayoutManager(this);
    }

    private void observeForever() {
        mViewModel.listQuestions.observeForever(quizModels -> quizAdapter.setQuestions(quizModels));
    }

    @Override
    public void onClick(int result) {
        if (result == CORRECT_ANSWER)
            mViewModel.onAnswerClick(true);
        else mViewModel.onAnswerClick(false);

        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                scroll();
            }

        }.start();
    }

    void scroll() {
        activityQuestionsBinding.progressBarQuestionActivity.setProgress(activityQuestionsBinding.progressBarQuestionActivity.getProgress() + 1);
        activityQuestionsBinding.recyclerview.scrollToPosition(activityQuestionsBinding.progressBarQuestionActivity.getProgress());
    }
}