package com.simple.colorful.setter;

import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;

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
		TypedArray a = newTheme.obtainStyledAttributes(themeId,
				new int[] { mAttrResId });
		int attributeResourceId = a.getResourceId(0, 0);
		Drawable drawable = mView.getResources().getDrawable(
				attributeResourceId);
		a.recycle();
		mView.setBackgroundDrawable(drawable);
	}

}
