package com.example.quizapp.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapters.SettingAdapter;
import com.example.quizapp.core.BaseFragment;
import com.example.quizapp.databinding.SettingFragmentBinding;
import com.example.quizapp.interfaces.OnSettingItemClickListener;
import com.example.quizapp.models.SettingItemModel;

public class SettingFragment extends BaseFragment implements OnSettingItemClickListener {

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
        adapter.addSetting(new SettingItemModel(Color.BLACK, "clear history", this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                LinearLayoutManager.VERTICAL);
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
    }


    @Override
    public void onSettingItemClick(String title) {
        if (title.equals("clear history")) {
            new AlertDialog.Builder(requireContext())
                    .setTitle("do you really want to clear history?")
                    .setPositiveButton("Yes", (dialog, which) -> App.getInstance().getQuizRepository().clearAll())
                    .setNegativeButton("No", null)
                    .show();
        }
    }

}
