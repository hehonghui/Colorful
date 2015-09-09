package com.simple.colorful.setter;

import android.content.res.Resources.Theme;
import android.widget.ListView;

public class ListViewSetter extends ViewGroupSetter {

	public ListViewSetter(ListView targetView, int resId) {
		super(targetView, resId);
	}

	@Override
	public void setValue(Theme newTheme, int themeId) {
		super.setValue(newTheme, themeId);
	}

}
