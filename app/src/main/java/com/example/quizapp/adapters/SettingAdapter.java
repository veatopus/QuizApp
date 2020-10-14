package com.example.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemSettingBinding;
import com.example.quizapp.models.SettingItemModel;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private List<SettingItemModel> data = new ArrayList<>();

    public void addSetting(SettingItemModel setting){
        data.add(setting);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SettingViewHolder(ItemSettingBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SettingViewHolder extends RecyclerView.ViewHolder {
        ItemSettingBinding binding;

        public SettingViewHolder(@NonNull ItemSettingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(SettingItemModel model){
            binding.setModel(model);
            binding.setHandlers(model.getOnSettingItemClickListener());
        }
    }
}