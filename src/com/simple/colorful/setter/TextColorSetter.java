package com.simple.colorful.setter;


import android.content.res.Resources.Theme;
import android.widget.TextView;

public class TextColorSetter extends ViewSetter {

	public TextColorSetter(TextView textView, int resId) {
		super(textView, resId);
	}

	@Override
	public void setValue(Theme newTheme,int themeId) {
		((TextView) mView).setTextColor(getColor(newTheme));
	}

}
