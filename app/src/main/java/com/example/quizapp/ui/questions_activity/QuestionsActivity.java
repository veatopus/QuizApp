package com.example.quizapp.ui.questions_activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.quizapp.R;
import com.example.quizapp.adapters.QuizAdapter;
import com.example.quizapp.castom_view.CustomGridLayoutManager;
import com.example.quizapp.databinding.ActivityQuestionsBinding;
import com.example.quizapp.interfaces.OnButtonAnswerClick;

public class QuestionsActivity extends AppCompatActivity implements OnButtonAnswerClick {
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
    }

    private void setArg() {
        mViewModel.setAmountQuestions(questionsAmount);
        activityQuestionsBinding.recyclerview.setAdapter(quizAdapter);
        activityQuestionsBinding.recyclerview.setLayoutManager(customGridLayoutManager);
        customGridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        activityQuestionsBinding.progressBarQuestionActivity.setMax(questionsAmount);
    }

    private void init() {
        mViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        activityQuestionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_questions);
        quizAdapter = new QuizAdapter(this);
        if (getIntent() != null)
            questionsAmount = getIntent().getIntExtra(RESULT_QUESTIONS_AMOUNT_KEY, 10);
        customGridLayoutManager = new CustomGridLayoutManager(this);
    }

    private void observeForever() {
        mViewModel.listQuestions.observeForever(quizModels -> quizAdapter.setQuestions(quizModels));
        mViewModel.answerAmount.observeForever(integer -> {
            activityQuestionsBinding.recyclerview.scrollToPosition(integer);
            activityQuestionsBinding.progressBarQuestionActivity.setProgress(integer);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view, int positionQuestion, int positionAnswer) {
        int result = mViewModel.onButtonClick(positionQuestion, positionAnswer);
        switch (result) {
            case QuestionsViewModel.CORRECT_ANSWER:
                view.setBackgroundResource(R.drawable.item_button_2);
                break;

            case QuestionsViewModel.CORRECT_ANSWER_AND_AND_FINAL_ANSWER:
                view.setBackgroundResource(R.drawable.item_button_2);
                finish();
                break;

            case QuestionsViewModel.WRONG_ANSWER:
                view.setBackgroundResource(R.drawable.item_button_3);
                break;

            case QuestionsViewModel.WRONG_ANSWER_AND_AND_FINAL_ANSWER:
                view.setBackgroundResource(R.drawable.item_button_3);
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + result);
        }
    }
}