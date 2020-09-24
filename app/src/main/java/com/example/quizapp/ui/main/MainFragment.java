package com.example.quizapp.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.ui.questions_activity.QuestionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    @BindView(R.id.seek_bar) public SeekBar seekBar;
    @BindView(R.id.text_view_questions_amount) TextView textViewQuestionsAmount;
    @BindView(R.id.button_minus_questions_amount) TextView buttonMinusQuestionsAmount;
    @BindView(R.id.button_plus_questions_amount) TextView buttonPlusQuestionsAmount;
    @BindView(R.id.btn_start) Button buttonStart;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        observeForever();
        seekBarInitialisation();
        setListener();
    }

    private void setListener() {
        buttonPlusQuestionsAmount.setOnClickListener(view -> mViewModel.buttonPlusCLicked());
        buttonMinusQuestionsAmount.setOnClickListener(view -> mViewModel.buttonMinusCLicked());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mViewModel.progressBarSuccess.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), QuestionsActivity.class);
            intent.putExtra(QuestionsActivity.RESULT_QUESTIONS_AMOUNT_KEY, mViewModel.progressBarSuccess.getValue());
            startActivity(intent);
        });
    }

    private void seekBarInitialisation() {
        seekBar.setMax(20);
        seekBar.setProgress(10 );
    }

    private void observeForever() {
        mViewModel.progressBarSuccess.observeForever(integer -> {
            textViewQuestionsAmount.setText(String.valueOf(integer));
            seekBar.setProgress(integer);
        });
    }
}