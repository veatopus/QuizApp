package com.example.quizapp.ui.questions_activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

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
        if (getIntent() != null) questionsAmount = getIntent().getIntExtra(RESULT_QUESTIONS_AMOUNT_KEY, 10);
        customGridLayoutManager = new CustomGridLayoutManager(this);
    }

    private void observeForever() {
        mViewModel.listQuestions.observeForever(quizModels -> quizAdapter.setQuestions(quizModels));
        mViewModel.answerAmount.observeForever(integer -> {
            activityQuestionsBinding.recyclerview.smoothScrollToPosition(integer + 1);
            activityQuestionsBinding.progressBarQuestionActivity.setProgress(integer + 1);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view, int positionQuestion, int positionAnswer) {
        Button button = (Button) view;
        view.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    button.setBackgroundResource(R.drawable.frame);
                    button.setTextAppearance(R.style.item_btn_text);
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    button.setBackgroundResource(R.drawable.item_button_4);
                    button.setTextAppearance(R.style.item_btn2_text);
                    return false; // if you want to handle the touch event
            }
            return false;
        });
        mViewModel.onButtonClick(view, positionQuestion, positionAnswer);
    }
}