package com.simple.colorful;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.simple.colorful.setter.ViewSetter;
import com.simple.colorful.setter.TextColorSetter;
import com.simple.colorful.setter.ViewBackgroundColorSetter;
import com.simple.colorful.setter.ViewBackgroundDrawableSetter;

/**
 * 主题切换库
 * 
 * @author mrsimple
 * 
 */
public final class Colorful {

	Builder mBuilder;

	private Colorful(Builder builder) {
		mBuilder = builder;
	}

	/**
	 * 设置新的主题
	 * 
	 * @param newTheme
	 */
	public void setTheme(int newTheme) {
		mBuilder.setTheme(newTheme);
	}

	/**
	 * 
	 * 构建Colorful的Builder对象
	 * 
	 * @author mrsimple
	 * 
	 */
	public static class Builder {
		/**
		 * 存储了视图和属性资源id的关系表
		 */
		Set<ViewSetter> mElements = new HashSet<ViewSetter>();
		/**
		 * 目标Activity
		 */
		Activity mActivity;

		/**
		 * 
		 * @param activity
		 */
		public Builder(Activity activity) {
			mActivity = activity;
		}

		/**
		 * 
		 * @param fragment
		 */
		public Builder(Fragment fragment) {
			mActivity = fragment.getActivity();
		}

		private View findViewById(int viewId) {
			return mActivity.findViewById(viewId);
		}

		/**
		 * 设置View的背景色
		 * 
		 * @param viewId
		 * @param colorId
		 * @return
		 */
		public Builder backgroundColor(int viewId, int colorId) {
			mElements.add(new ViewBackgroundColorSetter(findViewById(viewId),
					colorId));
			return this;
		}

		/**
		 * 设置View的drawable背景
		 * 
		 * @param viewId
		 * @param drawableId
		 * @return
		 */
		public Builder backgroundDrawable(int viewId, int drawableId) {
			mElements.add(new ViewBackgroundDrawableSetter(
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
		public Builder textColor(int viewId, int colorId) {
			TextView textView = (TextView) findViewById(viewId);
			mElements.add(new TextColorSetter(textView, colorId));
			return this;
		}

		/**
		 * 手动添加Setter
		 * 
		 * @param setter
		 * @return
		 */
		public Builder setter(ViewSetter setter) {
			mElements.add(setter);
			return this;
		}

		/**
		 * 设置新的主题
		 * 
		 * @param newTheme
		 */
		protected void setTheme(int newTheme) {
			mActivity.setTheme(newTheme);
			makeChange(newTheme);
		}

		/**
		 * 修改各个视图的背景色、文本颜色等
		 */
		private void makeChange(int themeId) {
			Theme curTheme = mActivity.getTheme();
			for (ViewSetter setter : mElements) {
				setter.setValue(curTheme, themeId);
			}
		}

		public Colorful create() {
			return new Colorful(this);
		}
	}
}
