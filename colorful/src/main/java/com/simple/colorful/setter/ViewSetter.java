package com.simple.colorful.setter;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;

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
	 * @return
	 */
	protected int getColor(Theme newTheme) {
		TypedValue typedValue = new TypedValue();
		newTheme.resolveAttribute(mAttrResId, typedValue, true);
		return typedValue.data;
	}

	public static abstract class AttrValueGetter<T> {

		public final void obtain(@NonNull Context context, Theme newTheme, int themeId, int attrId) {
			TypedArray a = newTheme.obtainStyledAttributes(themeId, new int[] { attrId });
			if ( a.length() > 0 ) {
				final int attributeResourceId = a.getResourceId(0, 0);
				T value = this.parseValue(context, newTheme, attributeResourceId);
				a.recycle();
				this.processValue(value);
			}
		}

		protected abstract T parseValue(@NonNull Context context, Theme newTheme, int resId);

		protected abstract void processValue(T value);
	}
}
