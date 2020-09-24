package com.example.quizapp.data.locally;

import com.example.quizapp.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.quizapp.models.QuizModel.TypeQuiz.*;

public class QuestionsBD {
    private List<QuizModel> quizModels;

    public QuestionsBD() {
        quizModels = new ArrayList<>();
        QuizModel quizModel;

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0)
                quizModel = new QuizModel(
                        "А что если бы сегодняший день, был бы последним в твоей жизни. \n" +
                                "Ты прожил его так же как проживаешь сейчас?",
                        new String[]{"да", "нет", "не знаю", "не могу ответить"},
                        "да",
                        OPTIONS);
            else
                quizModel = new QuizModel(
                        "А что если бы сегодняший день, был бы последним в твоей жизни. \n" +
                                "Ты прожил его так же как проживаешь сейчас?",
                        new String[]{"да", "нет"},
                        "да",
                        YES_NO);
            quizModels.add(quizModel);
        }
    }

    public List<QuizModel> getAll() {
        return quizModels;
    }

    public List<QuizModel> getQuestions(int quantity) {
        List<QuizModel> result = new ArrayList<>();
        if (quantity > quizModels.size()) result = getAll();
        else for (int i = 0; i < quantity; i++) result.add(quizModels.get(i));
        return result;
    }
}