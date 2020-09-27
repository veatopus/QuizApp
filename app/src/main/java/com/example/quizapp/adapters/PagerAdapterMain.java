package com.example.quizapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quizapp.ui.history.HistoryFragment;
import com.example.quizapp.ui.main.MainFragment;
import com.example.quizapp.ui.settings.SettingFragment;

public class PagerAdapterMain extends FragmentPagerAdapter {

    public PagerAdapterMain(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return HistoryFragment.newInstance();
            case 2:
                return SettingFragment.newInstance();

            default: return MainFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
