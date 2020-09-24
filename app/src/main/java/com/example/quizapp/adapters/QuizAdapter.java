package com.example.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemQuestionBinding;
import com.example.quizapp.interfaces.OnButtonAnswerClick;
import com.example.quizapp.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private List<QuizModel> quizModels = new ArrayList<>();
    private OnButtonAnswerClick answerClick;

    public QuizAdapter(OnButtonAnswerClick viewModel) {
        this.answerClick = viewModel;
    }

    public void setQuestions(List<QuizModel> quizModels) {
        this.quizModels = quizModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(quizModels.get(position));
    }

    @Override
    public int getItemCount() {
        return quizModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemQuestionBinding item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = ItemQuestionBinding.bind(itemView);
        }

        public void onBind(QuizModel quizModel){
            quizModel.setId(getAdapterPosition());
            item.setModel(quizModel);
            item.setHandlers(answerClick);
        }
    }
}
