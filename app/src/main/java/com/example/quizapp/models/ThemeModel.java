package com.example.quizapp.models;

public class ThemeModel {
    private int iconDrawableId;
    private boolean isChange;

    public ThemeModel(int iconDrawableId, boolean isChange) {
        this.iconDrawableId = iconDrawableId;
        this.isChange = isChange;
    }

    public int getIconDrawableId() {
        return iconDrawableId;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }
}
