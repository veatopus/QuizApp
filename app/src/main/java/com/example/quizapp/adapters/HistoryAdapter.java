package com.example.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemHistoryBinding;
import com.example.quizapp.models.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryModel> data = new ArrayList<>();

    public void addData(List<HistoryModel> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(ItemHistoryBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryBinding itemHistory;

        public HistoryViewHolder(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            itemHistory = binding;
        }

        void onBind(HistoryModel model){
            itemHistory.textViewCategory.setText(model.getCategory());
            itemHistory.textViewCorrectAns.setText(model.getCorrectAns());
            itemHistory.textViewDifficulty.setText(model.getDifficulty());
            itemHistory.textViewData.setText(model.getData());
        }
    }
}
