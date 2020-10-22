package com.example.quizapp.ui.display_setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapters.ThemeAdapter;
import com.example.quizapp.databinding.ActivityDisplaySettingBinding;
import com.example.quizapp.interfaces.OnThemeItemClickListener;

public class DisplaySettingActivity extends AppCompatActivity implements OnThemeItemClickListener {
    ActivityDisplaySettingBinding binding;
    ThemeAdapter adapter;
    DisplaySettingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPrefs().getTheme());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_setting);
        adapter = new ThemeAdapter();
        mViewModel = new ViewModelProvider(this).get(DisplaySettingViewModel.class);
        mViewModel.themes.observe(this, data -> adapter.setData(data));
        binding.recyclerviewTheme.setAdapter(adapter);
        adapter.onCLick(this);
        mViewModel.showTheme.observe(this, integer -> {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });
    }

    @Override
    public void onThemeClicked(int position) {
        mViewModel.onThemeChanged(position);
    }
}
