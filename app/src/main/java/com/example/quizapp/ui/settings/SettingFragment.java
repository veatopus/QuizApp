package com.example.quizapp.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.adapters.SettingAdapter;
import com.example.quizapp.core.BaseFragment;
import com.example.quizapp.databinding.SettingFragmentBinding;
import com.example.quizapp.models.SettingItemModel;

public class SettingFragment extends BaseFragment {

    private SettingViewModel mViewModel;
    SettingFragmentBinding binding;
    SettingAdapter adapter;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public int getLayoutID() {
        return R.layout.setting_fragment;
    }

    @Override
    protected void init(View view) {
        binding = SettingFragmentBinding.bind(view);
        adapter = new SettingAdapter();
        binding.recyclerview.setAdapter(adapter);
        adapter.addSetting(new SettingItemModel(Color.BLACK, "title"));
        adapter.addSetting(new SettingItemModel(Color.BLACK, "gggggg"));
        adapter.addSetting(new SettingItemModel(Color.BLUE, "fgggggggр"));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                LinearLayoutManager.VERTICAL);
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
    }


}
