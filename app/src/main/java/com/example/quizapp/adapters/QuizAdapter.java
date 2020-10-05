package com.example.quizapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemQuestionBinding;
import com.example.quizapp.interfaces.OnButtonAnswerClick;
import com.example.quizapp.interfaces.OnResultAnswerClickListener;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private List<Question> questionModels = new ArrayList<>();
    private OnResultAnswerClickListener answerClick;

    public void setAnswerClick(OnResultAnswerClickListener answerClick) {
        this.answerClick = answerClick;
    }

    public void setQuestions(List<Question> questionModels) {
        this.questionModels = questionModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuestionBinding binding = ItemQuestionBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false));
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(questionModels.get(position));
    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener, OnButtonAnswerClick {
        public static final int CORRECT_ANSWER = 1;
        public static final int CORRECT_ANSWER_AND_FINAL_ANSWER = 11;
        public static final int WRONG_ANSWER = 2;
        public static final int WRONG_ANSWER_AND_FINAL_ANSWER = 22;
        private ItemQuestionBinding item;
        private Vibrator vibrator;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull ItemQuestionBinding itemView) {
            super(itemView.getRoot());
            item = itemView;
            item.setHandlers(this);
            vibrator = (Vibrator) itemView.getRoot().getContext().getSystemService(Context.VIBRATOR_SERVICE);
        }

        @SuppressLint("ClickableViewAccessibility")
        private void onTouch() {
            item.button1.setOnTouchListener(this);
            item.button2.setOnTouchListener(this);
            item.button3.setOnTouchListener(this);
            item.button4.setOnTouchListener(this);
            item.type2Button.setOnTouchListener(this);
            item.type2Button1.setOnTouchListener(this);
        }

        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onBind(Question question) {
            Log.e("ololo", "onBind: ");
            item.button1.setBackgroundResource(R.drawable.item_button_4);
            item.button2.setBackgroundResource(R.drawable.item_button_4);
            item.button3.setBackgroundResource(R.drawable.item_button_4);
            item.button4.setBackgroundResource(R.drawable.item_button_4);
            item.type2Button.setBackgroundResource(R.drawable.item_button_4);
            item.type2Button1.setBackgroundResource(R.drawable.item_button_4);

            item.button1.setTextAppearance(R.style.item_btn2_text);
            item.button2.setTextAppearance(R.style.item_btn2_text);
            item.button3.setTextAppearance(R.style.item_btn2_text);
            item.button4.setTextAppearance(R.style.item_btn2_text);
            item.type2Button.setTextAppearance(R.style.item_btn2_text);
            item.type2Button1.setTextAppearance(R.style.item_btn2_text);

            question.getIsSkipClicked().observeForever(clickable -> {
                if (clickable) {
                    buttonClickable(false);
                    showCorrectButton(question);
                    onTouch();
                }
            });

            if (question.isChoice()) {
                switch (question.getUserChoice()) {
                    case 0:
                        item.button1.setBackgroundResource(R.drawable.item_button_3);
                        item.button1.setTextAppearance(R.style.item_btn_text);
                        break;
                    case 1:
                        item.button2.setBackgroundResource(R.drawable.item_button_3);
                        item.button2.setTextAppearance(R.style.item_btn_text);
                        break;
                    case 2:
                        item.button3.setBackgroundResource(R.drawable.item_button_3);
                        item.button3.setTextAppearance(R.style.item_btn_text);
                        break;
                    case 3:
                        item.button4.setBackgroundResource(R.drawable.item_button_3);
                        item.button4.setTextAppearance(R.style.item_btn_text);
                        break;
                }
                Log.e("ololo", "onBind: skip is work");
                onTouch();
                showCorrectButton(question);
                buttonClickable(false);
            } else {
                onTouch();
                buttonClickable(true);
            }
            item.setModel(question);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void showCorrectButton(Question question) {
            String correctAnc = question.getCorrectAnswer();
            int positionCorrectAnc = 0;
            for (int i = 0; i < question.getIncorrectAnswers().size(); i++) {
                if (correctAnc.equals(question.getIncorrectAnswers().get(i)))
                    positionCorrectAnc = i;
            }
            switch (positionCorrectAnc) {
                case 0:
                    item.button1.setBackgroundResource(R.drawable.item_button_2);
                    item.button1.setTextAppearance(R.style.item_btn_text);

                    item.type2Button.setBackgroundResource(R.drawable.item_button_2);
                    item.type2Button.setTextAppearance(R.style.item_btn_text);
                    break;
                case 1:
                    item.button2.setBackgroundResource(R.drawable.item_button_2);
                    item.button2.setTextAppearance(R.style.item_btn_text);

                    item.type2Button1.setBackgroundResource(R.drawable.item_button_2);
                    item.type2Button1.setTextAppearance(R.style.item_btn_text);
                    break;
                case 2:
                    item.button3.setBackgroundResource(R.drawable.item_button_2);
                    item.button3.setTextAppearance(R.style.item_btn_text);
                    break;
                case 3:
                    item.button4.setBackgroundResource(R.drawable.item_button_2);
                    item.button4.setTextAppearance(R.style.item_btn_text);
                    break;
            }

        }


        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (item.getModel().getIsSkipClicked().getValue() != null)
                if (item.getModel().getIsSkipClicked().getValue()) return false;
            Button button = (Button) v;
            switch (event.getAction()) {
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

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View view, int positionQuestion, int positionAnswer) {
            item.getModel().setChoice(true);
            item.getModel().setUserChoice(positionAnswer);
            item.getModel().setSkipClicked(true);
            Button button = (Button) view;
            int result;
            Question questionModel = Objects.requireNonNull(questionModels.get(getAdapterPosition()));
            String userAnswer = questionModel.getIncorrectAnswers().get(positionAnswer);
            if (userAnswer.equals(questionModel.getCorrectAnswer())) {
                if (getAdapterPosition() >= questionModels.size() - 1) {
                    button.setBackgroundResource(R.drawable.item_button_2);
                    result = CORRECT_ANSWER_AND_FINAL_ANSWER;
                    button.setTextAppearance(R.style.item_btn_text);
                } else {
                    button.setBackgroundResource(R.drawable.item_button_2);
                    result = CORRECT_ANSWER;
                    button.setTextAppearance(R.style.item_btn_text);
                }
                vibration(50);
            } else {
                if (getAdapterPosition() >= questionModels.size() - 1) {
                    button.setBackgroundResource(R.drawable.item_button_3);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    button.setTextAppearance(R.style.item_btn_text);
                    result = WRONG_ANSWER_AND_FINAL_ANSWER;
                } else {
                    button.setTextAppearance(R.style.item_btn_text);
                    button.setBackgroundResource(R.drawable.item_button_3);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    result = WRONG_ANSWER;
                }
                vibration(400);
            }
            buttonClickable(false);
            showCorrectButton(item.getModel());
            answerClick.onClick(result);
        }

        private void buttonClickable(boolean clickable) {
            item.button1.setClickable(clickable);
            item.button2.setClickable(clickable);
            item.button3.setClickable(clickable);
            item.button4.setClickable(clickable);
            item.type2Button.setClickable(clickable);
            item.type2Button1.setClickable(clickable);
        }

        private void vibration(int count) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vibrator.vibrate(VibrationEffect.createOneShot(count, VibrationEffect.DEFAULT_AMPLITUDE));
            else vibrator.vibrate(count);
        }
    }
}