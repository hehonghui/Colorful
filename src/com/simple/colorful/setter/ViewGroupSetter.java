package com.simple.colorful.setter;

import java.util.HashSet;
import java.util.Set;

import android.content.res.Resources.Theme;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * TODO : Color与Drawable的设计问题,这类需要修改为桥接模式 {@see ViewBackgroundColorSetter}、
 * {@see ViewBackgroundDrawableSetter}
 * 
 * 
 * @author mrsimple
 * 
 */
public class ViewGroupSetter extends ViewSetter {

	/**
	 * ListView的子试图的Setter
	 */
	protected Set<ViewSetter> mItemViewSetters = new HashSet<ViewSetter>();

	/**
	 * 
	 * @param targetView
	 * @param resId
	 */
	public ViewGroupSetter(ViewGroup targetView, int resId) {
		super(targetView, resId);
	}

	/**
	 * 
	 * @param viewId
	 * @return
	 */
	private View findViewById(int viewId) {
		return mView.findViewById(viewId);
	}

	/**
	 * 设置View的背景色
	 * 
	 * @param viewId
	 * @param colorId
	 * @return
	 */
	public ViewGroupSetter childViewBgColor(int viewId, int colorId) {
		mItemViewSetters.add(new ViewBackgroundColorSetter(
				findViewById(viewId), colorId));
		return this;
	}

	/**
	 * 设置View的drawable背景
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewGroupSetter childViewBgDrawable(int viewId, int drawableId) {
		mItemViewSetters.add(new ViewBackgroundDrawableSetter(
				findViewById(viewId), drawableId));
		return this;
	}

	/**
	 * 设置文本颜色,因此View的类型必须为TextView或者其子类
	 * 
	 * @param viewId
	 * @param colorId
	 * @return
	 */
	public ViewGroupSetter childViewTextColor(int viewId, int colorId) {
		TextView textView = (TextView) findViewById(viewId);
		mItemViewSetters.add(new TextColorSetter(textView, colorId));
		return this;
	}

	@Override
	public void setValue(Theme newTheme, int themeId) {
		mView.setBackgroundColor(getColor(newTheme));

		// 修改所有子元素的相关属性
		changeChildenAttrs((ViewGroup) mView, newTheme, themeId);
	}

	/**
	 * 
	 * @param viewGroup
	 * @param newTheme
	 * @param themeId
	 */
	private void changeChildenAttrs(ViewGroup viewGroup, Theme newTheme,
			int themeId) {
		int childCount = viewGroup.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = viewGroup.getChildAt(i);
			// 深度遍历
			if (childView instanceof ViewGroup) {
				changeChildenAttrs((ViewGroup) childView, newTheme, themeId);
			}

			// 遍历子元素与要修改的属性,如果相同那么则修改子View的属性
			for (ViewSetter setter : mItemViewSetters) {
				Log.e("", "### childView : " + childView);
				Log.e("", "### setter view : " + setter.mView);
				if (childView.getId() == setter.getViewId()) {
					setter.setValue(newTheme, themeId);
				}
			}
		}
	}

}
