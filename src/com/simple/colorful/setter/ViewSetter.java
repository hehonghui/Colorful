package com.simple.colorful.setter;

import android.content.res.Resources.Theme;
import android.util.TypedValue;
import android.view.View;

/**
 * ViewSetter，用于通过{@see #mAttrResId}
 * 设置View的某个属性值,例如背景Drawable、背景色、文本颜色等。如需修改其他属性,可以自行扩展ViewSetter.
 * 
 * @author mrsimple
 * 
 */
public abstract class ViewSetter {

	/**
	 * 目标View
	 */
	protected View mView;
	/**
	 * 目标view id,有时在初始化时还未构建该视图,比如ListView的Item View中的某个控件
	 */
	protected int mViewId;
	/**
	 * 目标View要的特定属性id
	 */
	protected int mAttrResId;

	public ViewSetter(View targetView, int resId) {
		mView = targetView;
		mAttrResId = resId;
	}

	public ViewSetter(int viewId, int resId) {
		mViewId = viewId;
		mAttrResId = resId;
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

	protected boolean isViewNotFound() {
		return mView == null;
	}

	/**
	 * 
	 * @param newTheme
	 * @param resId
	 * @return
	 */
	protected int getColor(Theme newTheme) {
		TypedValue typedValue = new TypedValue();
		newTheme.resolveAttribute(mAttrResId, typedValue, true);
		return typedValue.data;
	}
}
