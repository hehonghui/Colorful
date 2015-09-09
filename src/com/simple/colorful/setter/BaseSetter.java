package com.simple.colorful.setter;

import android.content.res.Resources.Theme;
import android.util.TypedValue;
import android.view.View;

/**
 * 
 * @author mrsimple
 *
 */
public abstract class BaseSetter {

	protected View mView;
	protected int mResId;

	public BaseSetter(View targetView, int resId) {
		mView = targetView;
		mResId = resId;
	}

	public abstract void setValue(Theme newTheme, int themeId);

	protected int getColor(Theme newTheme, int resId) {
		TypedValue typedValue = new TypedValue();
		newTheme.resolveAttribute(resId, typedValue, true);
		return typedValue.data;
	}
}
