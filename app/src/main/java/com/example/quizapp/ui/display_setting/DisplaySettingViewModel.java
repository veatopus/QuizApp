package com.example.quizapp.ui.display_setting;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class DisplaySettingViewModel extends ViewModel {
    public MutableLiveData<List<Integer>> themes = new MutableLiveData<>();
    public MutableLiveData<Integer> showTheme = new MutableLiveData<>();


    public DisplaySettingViewModel() {
        main();
    }

    private void main() {
        List<Integer> data = new ArrayList<>();
        data.add(R.drawable.dark_theme_icon);
        data.add(R.drawable.green_theme_icon);
        data.add(R.drawable.light_theme_icon);
        data.add(R.drawable.orange_theme_icon);
        themes.setValue(data);
    }

    @SuppressLint("NonConstantResourceId")
    public void onThemeChanged(int position) {
        switch (position) {
            case 0:
                App.getInstance().getPrefs().isTheme(R.style.AppDarkTheme);
                showTheme.setValue(R.style.AppDarkTheme);
                break;
            case 1:
                App.getInstance().getPrefs().isTheme(R.style.AppGreenTheme);
                showTheme.setValue(R.style.AppGreenTheme);
                break;
            case 2:
                App.getInstance().getPrefs().isTheme(R.style.AppLightTheme);
                showTheme.setValue(R.style.AppLightTheme);
                break;
            case 3:
                App.getInstance().getPrefs().isTheme(R.style.AppOrangeTheme);
                showTheme.setValue(R.style.AppOrangeTheme);
                break;
        }
    }

}
