package com.example.quizapp.ui.display_setting;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.models.ThemeModel;

import java.util.ArrayList;
import java.util.List;

public class DisplaySettingViewModel extends ViewModel {
    public MutableLiveData<List<ThemeModel>> themes = new MutableLiveData<>();
    public MutableLiveData<Integer> showTheme = new MutableLiveData<>();


    public DisplaySettingViewModel() {
        main();
    }

    private void main() {
        List<ThemeModel> data = new ArrayList<>();
        data.add(new ThemeModel(R.drawable.dark_theme_icon, false));
        data.add(new ThemeModel(R.drawable.green_theme_icon, false));
        data.add(new ThemeModel(R.drawable.light_theme_icon, false));
        data.add(new ThemeModel(R.drawable.orange_theme_icon, false));
        data.get(App.getInstance().getPrefs().getThemePosition()).setChange(true);
        themes.setValue(data);
    }

    @SuppressLint("NonConstantResourceId")
    public void onThemeChanged(int position) {
        switch (position) {
            case 0:
                if (App.getInstance().getPrefs().getTheme() != R.style.AppDarkTheme) {
                    App.getInstance().getPrefs().setTheme(R.style.AppDarkTheme);
                    showTheme.setValue(R.style.AppDarkTheme);
                }
                break;
            case 1:
                if (App.getInstance().getPrefs().getTheme() != R.style.AppGreenTheme) {
                    App.getInstance().getPrefs().setTheme(R.style.AppGreenTheme);
                    showTheme.setValue(R.style.AppGreenTheme);
                }
                break;
            case 2:
                if (App.getInstance().getPrefs().getTheme() != R.style.AppLightTheme) {
                    App.getInstance().getPrefs().setTheme(R.style.AppLightTheme);
                    showTheme.setValue(R.style.AppLightTheme);
                }
                break;
            case 3:
                if (App.getInstance().getPrefs().getTheme() != R.style.AppOrangeTheme) {
                    App.getInstance().getPrefs().setTheme(R.style.AppOrangeTheme);
                    showTheme.setValue(R.style.AppOrangeTheme);
                }
                break;
        }
        App.getInstance().getPrefs().setThemePosition(position);
        Log.e("ololo", "onThemeChanged: " + App.getInstance().getPrefs().getTheme());
    }

}
