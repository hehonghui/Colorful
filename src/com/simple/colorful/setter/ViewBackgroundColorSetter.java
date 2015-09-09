package com.simple.colorful.setter;

import android.content.res.Resources.Theme;
import android.view.View;

public class ViewBackgroundColorSetter extends ViewSetter {

	public ViewBackgroundColorSetter(View target, int resId) {
		super(target, resId);
	}

	@Override
	public void setValue(Theme newTheme, int themeId) {
		if ( mView != null ) {
			mView.setBackgroundColor(getColor(newTheme));
		}
	}

}
