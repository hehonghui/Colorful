package com.simple.colorful.setter;

import android.content.res.Resources.Theme;
import android.widget.TextView;
/**
 * TextView 文本颜色Setter
 * @author mrsimple
 *
 */
public class TextColorSetter extends ViewSetter {

	public TextColorSetter(TextView textView, int attrId) {
		super(textView, attrId);
	}

	public TextColorSetter(int viewId, int attrId) {
		super(viewId, attrId);
	}

	@Override
	public void setValue(Theme newTheme, int themeId) {
		if (mView == null) {
			return;
		}
		((TextView) mView).setTextColor(getColor(newTheme));
	}
}
