package com.jr.android.uitoolkit.view;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class ColoredTextTwoLineListAdapter implements ITwoLineListItem {
    private String lineOneText;
    private String lineTwoText;
    private Integer color;

    public ColoredTextTwoLineListAdapter(String lineOneText, String lineTwoText, Integer color) {
        this.lineOneText = lineOneText;
        this.lineTwoText = lineTwoText;
        this.color       = color;
    }

    public String getLineOneText() {
        return this.lineOneText;
    }

    public String getLineTwoText() {
        return this.lineTwoText;
    }
    
    @Override
    public Drawable getBackground() {
        return null;
    }
    
    public Integer getTextColor() {
        return this.color;
    }
}
