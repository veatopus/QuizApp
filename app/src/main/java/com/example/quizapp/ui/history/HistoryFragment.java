package com.example.quizapp.ui.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.adapters.HistoryAdapter;
import com.example.quizapp.databinding.HistoryFragmentBinding;
import com.example.quizapp.databinding.ItemHistoryBinding;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private HistoryFragmentBinding historyFragmentBinding;
    private HistoryAdapter adapter;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyFragmentBinding = HistoryFragmentBinding.bind(inflater.inflate(R.layout.history_fragment, container, false));
        return historyFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        init();
        mViewModel.updateData();
        historyFragmentBinding.recyclerview.setAdapter(adapter);
        mViewModel.listHistoryMutableLiveData.observeForever(historyModels -> adapter.addData(historyModels));
    }


    private void init() {
        adapter = new HistoryAdapter();
    }
}
