package com.simple.colorful.setter;

import android.content.res.Resources.Theme;
import android.util.TypedValue;
import android.view.View;

/**
 * 
 * @author mrsimple
 * 
 */
public abstract class ViewSetter {

	protected View mView;
	protected int mResId;

	public ViewSetter(View targetView, int resId) {
		mView = targetView;
		mResId = resId;
	}

	/**
	 * 
	 * @param newTheme
	 * @param themeId
	 */
	public abstract void setValue(Theme newTheme, int themeId);

	/**
	 * 获取视图的Id
	 * 
	 * @return
	 */
	protected int getViewId() {
		return mView != null ? mView.getId() : -1;
	}

	/**
	 * 
	 * @param newTheme
	 * @param resId
	 * @return
	 */
	protected int getColor(Theme newTheme) {
		TypedValue typedValue = new TypedValue();
		newTheme.resolveAttribute(mResId, typedValue, true);
		return typedValue.data;
	}
}
