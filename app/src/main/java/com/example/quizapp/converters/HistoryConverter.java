package com.example.quizapp.converters;

import androidx.room.TypeConverter;

import com.example.quizapp.models.Answers;

import java.util.Arrays;
import java.util.List;

public class HistoryConverter {

    @TypeConverter
    public Answers storedStringToLanguages(String value) {
        List<String> langs = Arrays.asList(value.split("\\s*,\\s*"));
        return new Answers(langs);
    }

    @TypeConverter
    public String languagesToStoredString(Answers cl) {
        StringBuilder value = new StringBuilder();

        for (String lang :cl.getAnswers())
            value.append(lang).append(",");

        return value.toString();
    }
}