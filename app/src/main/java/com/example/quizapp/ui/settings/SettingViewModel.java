package com.example.quizapp.ui.settings;

import android.graphics.Color;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.interfaces.OnSettingItemClickListener;
import com.example.quizapp.models.SettingItemModel;

import java.util.ArrayList;
import java.util.List;

public class SettingViewModel extends ViewModel implements OnSettingItemClickListener {
    MutableLiveData<List<SettingItemModel>> setting = new MutableLiveData<>();
    MutableLiveData<Boolean> showDialogDeleteHistory = new MutableLiveData<>(false);
    MutableLiveData<Boolean> showToastSuccessDelete = new MutableLiveData<>();
    MutableLiveData<Boolean> showDisplaySetting = new MutableLiveData<>();

    public SettingViewModel() {
        main();
    }

    private void main() {
        List<SettingItemModel> setting = new ArrayList<>();
        setting.add(new SettingItemModel(Color.rgb(205, 0, 205), "clear history", this));
        setting.add(new SettingItemModel("display settings", this));
        this.setting.setValue(setting);
    }

    @Override
    public void onSettingItemClick(String title) {
        switch (title) {
            case "clear history":
                showDialogDeleteHistory.setValue(true);
                break;

                case "display settings":
                showDisplaySetting.setValue(true);
                break;
        }
    }

    public void onYesDeleteDialogClicked(){
        App.getInstance().getQuizRepository().clearAll();
        showToastSuccessDelete.setValue(true);
    }
}
