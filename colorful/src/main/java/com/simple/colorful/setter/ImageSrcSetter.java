package com.simple.colorful.setter;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

/**
 * ImageView src Setter
 * @author mrsimple
 * 
 */
public final class ImageSrcSetter extends ViewSetter {

	public ImageSrcSetter(ImageView targetView, int attrId) {
		super(targetView, attrId);
	}

	public ImageSrcSetter(@IdRes int viewId, int attrId) {
		super(viewId, attrId);
	}

	@Override
	public void setValue(Theme newTheme, int themeId) {
		if ( mView == null || !(mView instanceof ImageView)) {
			return  ;
		}
		new AttrValueGetter<Drawable>() {

			@Override
			protected Drawable parseValue(@NonNull Context context, Theme newTheme, int attributeResourceId) {
				return mView.getResources().getDrawable(attributeResourceId, newTheme);
			}

			@Override
			protected void processValue(Drawable value) {
				ImageView imageView = (ImageView) mView;
				imageView.setImageDrawable(value);
			}
		}.obtain(mView.getContext(), newTheme, themeId, mAttrResId);
	}
}
