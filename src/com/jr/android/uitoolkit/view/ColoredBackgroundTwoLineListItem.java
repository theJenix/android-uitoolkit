package com.jr.android.uitoolkit.view;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class ColoredBackgroundTwoLineListItem implements ITwoLineListItem {

	private String lineOneText;
	private String lineTwoText;
    private int color;

	public ColoredBackgroundTwoLineListItem(String lineOneText, String lineTwoText, int color) {
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
	    return new ColorDrawable(this.color);
	}
	
	public Integer getTextColor() {
	    return null;
	}
}
