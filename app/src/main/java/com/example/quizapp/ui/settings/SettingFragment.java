package com.example.quizapp.ui.settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapters.SettingAdapter;
import com.example.quizapp.core.BaseFragment;
import com.example.quizapp.databinding.SettingFragmentBinding;
import com.example.quizapp.ui.display_setting.DisplaySettingActivity;

public class SettingFragment extends BaseFragment {

    private SettingViewModel mViewModel;
    private SettingFragmentBinding binding;
    private SettingAdapter adapter;
    private DividerItemDecoration dividerItemDecoration;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public int getLayoutID() {
        return R.layout.setting_fragment;
    }

    @Override
    protected void init(View view) {
        requireActivity().setTheme(App.getInstance().getPrefs().getTheme());
        binding = SettingFragmentBinding.bind(view);
        adapter = new SettingAdapter();
        dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(), LinearLayoutManager.VERTICAL);
    }

    @Override
    protected void observe(LifecycleOwner owner) {
        mViewModel = new ViewModelProvider(requireActivity()).get(SettingViewModel.class);
        mViewModel.showDialogDeleteHistory.observe(owner, this::showDialogClearHistory);
        mViewModel.showToastSuccessDelete.observe(owner, this::showToastHistorySuccessDeleted);
        mViewModel.setting.observe(owner, settings -> adapter.setAllSetting(settings));
        mViewModel.showDisplaySetting.observe(owner, this::showDisplaySettings);
    }

    @Override
    protected void setArg() {
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
    }

    private void showDialogClearHistory(Boolean isShow) {
        if (isShow) new AlertDialog.Builder(requireContext())
                .setTitle("do you really want to clear history?")
                .setPositiveButton("Yes", (dialog, which) -> mViewModel.onYesDeleteDialogClicked())
                .setNegativeButton("No", null)
                .show();
    }

    private void showToastHistorySuccessDeleted(Boolean isShow) {
        if (isShow) showToast("history has been successfully deleted");
    }

    private void showDisplaySettings(Boolean isShow) {
        if (isShow) startActivity(new Intent(requireActivity(), DisplaySettingActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTheme(App.getInstance().getPrefs().getTheme());
    }


}
