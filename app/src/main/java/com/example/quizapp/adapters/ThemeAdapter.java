package com.example.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemThemeBinding;
import com.example.quizapp.interfaces.OnThemeItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    private List<Integer> data = new ArrayList<>();
    private OnThemeItemClickListener listener;

    public void onCLick(OnThemeItemClickListener listener){
        this.listener = listener;
    }

    public void setData(List<Integer> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemThemeBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemThemeBinding binding;

        public ViewHolder(@NonNull ItemThemeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> {
                binding.radioButton.setChecked(!binding.radioButton.isChecked());
                if (binding.radioButton.isChecked())
                    listener.onThemeClicked(getAdapterPosition());
            });

        }

        public void onBind(int drawableTheme){
            binding.imageViewTheme.setImageDrawable(ContextCompat.getDrawable(binding.getRoot().getContext(), drawableTheme));
        }
    }
}
