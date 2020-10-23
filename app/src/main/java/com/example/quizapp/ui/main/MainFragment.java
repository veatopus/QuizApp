package com.example.quizapp.ui.main;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.core.ConnectionLiveData;
import com.example.quizapp.databinding.MainFragmentBinding;
import com.example.quizapp.interfaces.shortInterfaces.ItemSelectedListener;
import com.example.quizapp.interfaces.shortInterfaces.SeekBarChangeListener;
import com.example.quizapp.models.TriviaCategory;
import com.example.quizapp.ui.questions_activity.QuestionsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private int category = 99;
    private String stringCategory = "Any category";
    private String difficulty = "Any type";
    private ConnectionLiveData connectionLiveData;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        return binding.getRoot();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        connectionLiveData = new ConnectionLiveData(requireContext());
        binding.setViewModel(mViewModel);
        observeForever();
        setListener();
    }

    private void setListener() {
        binding.seekBar.setOnSeekBarChangeListener(new SeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mViewModel.progressBarSuccess.setValue(i);
            }
        });

        binding.difficultySpinner.setOnItemSelectedListener(new ItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficulty = getResources().getStringArray(R.array.spinner_category_example)[position];
            }
        });
    }

    private void observeForever() {
        mViewModel.progressBarSuccess.observeForever(integer -> {
            binding.textViewQuestionsAmount.setText(String.valueOf(integer));
            binding.seekBar.setProgress(integer);
        });
        mViewModel.triviaCategories.observeForever(triviaCategories -> {
            List<String> name_category = new ArrayList<>();
            for (TriviaCategory triviaCategory : triviaCategories.getTriviaCategories())
                name_category.add(triviaCategory.getName());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(binding.getRoot().getContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.categorySpinner.setAdapter(adapter);
            binding.categorySpinner.setOnItemSelectedListener(new ItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    category = triviaCategories.getTriviaCategories().get(position).getId();
                    stringCategory = triviaCategories.getTriviaCategories().get(position).getName();
                }
            });
        });

        mViewModel.isStart.observeForever(aBoolean -> {
            if (aBoolean) {
                Intent intent = new Intent(requireActivity(), QuestionsActivity.class);
                intent.putExtra(QuestionsActivity.RESULT_QUESTIONS_AMOUNT_KEY, mViewModel.progressBarSuccess.getValue());
                intent.putExtra(QuestionsActivity.RESULT_CATEGORY_KEY, category);
                intent.putExtra(QuestionsActivity.RESULT_DIFFICULTY_KEY, difficulty);
                intent.putExtra(QuestionsActivity.RESULT_TITLE_KEY, stringCategory);
                startActivity(intent);
            }
        });

        connectionLiveData.observe(requireActivity(), aBoolean -> {
            mViewModel.setDoesHaveInternet(aBoolean);
            mViewModel.updateCategory();
        });

        mViewModel.isShowDialogNoInternet.observe(requireActivity(), this::isShowAlertDialog);
    }

    private void isShowAlertDialog(Boolean isShowDialogNoInternet) {
        if (isShowDialogNoInternet) new AlertDialog.Builder(requireContext())
                .setTitle("Sorry, No Internet Connection")
                .setMessage("Check your network setting and try again")
                .setPositiveButton("Ok", null)
                .show();
    }

}