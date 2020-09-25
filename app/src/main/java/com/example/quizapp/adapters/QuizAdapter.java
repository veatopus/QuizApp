package com.example.quizapp.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        private ItemQuestionBinding item;
        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = ItemQuestionBinding.bind(itemView);
            item.button1.setOnTouchListener(this);
            item.button2.setOnTouchListener(this);
            item.button3.setOnTouchListener(this);
            item.button4.setOnTouchListener(this);
            item.type2Button.setOnTouchListener(this);
            item.type2Button1.setOnTouchListener(this);
        }

        public void onBind(QuizModel quizModel){
            quizModel.setId(getAdapterPosition());
            item.setModel(quizModel);
            item.setHandlers(answerClick);
            item.button1.setBackgroundResource(R.drawable.item_button_4);
            item.button2.setBackgroundResource(R.drawable.item_button_4);
            item.button3.setBackgroundResource(R.drawable.item_button_4);
            item.button4.setBackgroundResource(R.drawable.item_button_4);
            item.type2Button.setBackgroundResource(R.drawable.item_button_4);
            item.type2Button1.setBackgroundResource(R.drawable.item_button_4);
        }



        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Button button = (Button) v;
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    button.setBackgroundResource(R.drawable.frame);
                    button.setTextAppearance(R.style.item_btn_text);
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    button.setBackgroundResource(R.drawable.item_button_4);
                    button.setTextAppearance(R.style.item_btn2_text);
                    return false; // if you want to handle the touch event
            }
            return false;
        }
    }
}
