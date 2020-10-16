package com.example.quizapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemHistoryBinding;
import com.example.quizapp.generated.callback.OnClickListener;
import com.example.quizapp.interfaces.OnPopupMenuClickListener;
import com.example.quizapp.models.HistoryResultModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryResultModel> data = new ArrayList<>();
    private OnPopupMenuClickListener onPopupMenuClick;

    public void setOnPopupMenuClick(OnPopupMenuClickListener onPopupMenuClick) {
        this.onPopupMenuClick = onPopupMenuClick;
    }

    public void addData(List<HistoryResultModel> data) {
        this.data = data;
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
        private final ItemHistoryBinding itemHistory;

        public HistoryViewHolder(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            itemHistory = binding;
            binding.setHandlers(onPopupMenuClick);
            binding.popUpMenu.setOnClickListener(v -> onPopupMenuClick.onPopupMenuClick(v, getAdapterPosition()));
        }

        @SuppressLint("SetTextI18n")
        void onBind(HistoryResultModel model){
            itemHistory.textViewCategory.setText(model.getCategory());
            itemHistory.textViewCorrectAns.setText(model.getCorrectAns() + "");
            itemHistory.textViewDifficulty.setText(model.getDifficulty());
            itemHistory.textViewData.setText(model.getDate());
        }
    }
}