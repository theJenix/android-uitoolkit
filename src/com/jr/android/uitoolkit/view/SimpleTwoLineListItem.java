package com.jr.android.uitoolkit.view;

import android.graphics.drawable.Drawable;

public class SimpleTwoLineListItem implements ITwoLineListItem {

	private String lineOneText;
	private String lineTwoText;

	public SimpleTwoLineListItem(String lineOneText, String lineTwoText) {
		this.lineOneText = lineOneText;
		this.lineTwoText = lineTwoText;
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
	
	@Override
	public Integer getTextColor() {
	    // TODO Auto-generated method stub
	    return null;
	}
}
