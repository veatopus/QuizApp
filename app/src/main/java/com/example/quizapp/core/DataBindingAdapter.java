package com.example.quizapp.core;

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapter {

    @BindingAdapter({"app:color","app:colorAttrId"})
    public static void setColorFromAttr(TextView view, int color, int textAttrId){
        int endColor = color;

        if (endColor == 0){
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = view.getContext().getTheme();
            theme.resolveAttribute(textAttrId, typedValue, true);
            endColor = typedValue.data;
        }

        view.setTextColor(endColor);
    }
}
