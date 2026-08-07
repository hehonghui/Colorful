package com.simple.colorful.setter;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * View的背景Drawabler Setter
 * @author mrsimple
 * 
 */
public final class ViewBackgroundDrawableSetter extends ViewSetter {

	public ViewBackgroundDrawableSetter(View targetView, int resId) {
		super(targetView, resId);
	}
	
	
	public ViewBackgroundDrawableSetter(int viewId, int resId) {
		super(viewId, resId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setValue(Theme newTheme, int themeId) {
		if ( mView == null ) {
			return  ;
		}
//		TypedArray a = newTheme.obtainStyledAttributes(themeId,
//				new int[] { mAttrResId });
//		if ( a.length() > 0 ) {
//			int attributeResourceId = a.getResourceId(0, 0);
//			Drawable drawable = mView.getResources().getDrawable(
//					attributeResourceId);
//			a.recycle();
//			mView.setBackgroundDrawable(drawable);
//		}

		new AttrValueGetter<Drawable>() {

			@Override
			protected Drawable parseValue(@NonNull Context context, Theme newTheme, int attributeResourceId) {
				return mView.getResources().getDrawable(attributeResourceId);
			}

			@Override
			protected void processValue(Drawable value) {
				mView.setBackgroundDrawable(value);
			}
		}.obtain(mView.getContext(), newTheme, themeId, mAttrResId);
	}
}
