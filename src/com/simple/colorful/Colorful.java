package com.simple.colorful;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.simple.colorful.setter.TextColorSetter;
import com.simple.colorful.setter.ViewBackgroundColorSetter;
import com.simple.colorful.setter.ViewBackgroundDrawableSetter;
import com.simple.colorful.setter.ViewSetter;

/**
 * 主题切换控制类
 * 
 * @author mrsimple
 * 
 */
public final class Colorful {
	/**
	 * Colorful Builder
	 */
	Builder mBuilder;

	/**
	 * private constructor
	 * 
	 * @param builder
	 */
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
		 * 将View id与存储该view背景色的属性进行绑定
		 * 
		 * @param viewId
		 *            控件id
		 * @param colorId
		 *            颜色属性id
		 * @return
		 */
		public Builder backgroundColor(int viewId, int colorId) {
			mElements.add(new ViewBackgroundColorSetter(findViewById(viewId),
					colorId));
			return this;
		}

		/**
		 * 将View id与存储该view背景Drawable的属性进行绑定
		 * 
		 * @param viewId
		 *            控件id
		 * @param colorId
		 *            Drawable属性id
		 * @return
		 */
		public Builder backgroundDrawable(int viewId, int drawableId) {
			mElements.add(new ViewBackgroundDrawableSetter(
					findViewById(viewId), drawableId));
			return this;
		}

		/**
		 * 将TextView id与存储该TextView文本颜色的属性进行绑定
		 * 
		 * @param viewId
		 *            TextView或者TextView子类控件的id
		 * @param colorId
		 *            颜色属性id
		 * @return
		 */
		public Builder textColor(int viewId, int colorId) {
			TextView textView = (TextView) findViewById(viewId);
			mElements.add(new TextColorSetter(textView, colorId));
			return this;
		}

		/**
		 * 用户手动构造并且添加Setter
		 * 
		 * @param setter
		 *            用户自定义的Setter
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
		 * 修改各个视图绑定的属性
		 */
		private void makeChange(int themeId) {
			Theme curTheme = mActivity.getTheme();
			for (ViewSetter setter : mElements) {
				setter.setValue(curTheme, themeId);
			}
		}

		/**
		 * 创建Colorful对象
		 * 
		 * @return
		 */
		public Colorful create() {
			return new Colorful(this);
		}
	}
}
