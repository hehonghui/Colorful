package com.simple.colorful.app;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.simple.colorful.Colorful;
import com.simple.colorful.setter.ViewGroupSetter;

/**
 * 演示换肤的用法,含义按钮、TextView、ListView
 * 
 * @author mrsimple
 * 
 */
public class MainActivity extends AppCompatActivity {

	ListView mNewsListView;
	List<String> mNewsList = new ArrayList<String>();
	Colorful mColorful;
	boolean isNight = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_view), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		// 换肤事件
		findViewById(R.id.change_btn).setOnClickListener(v -> changeThemeWithColorful());

		// 第二个Activity,显示的是RecyclerVoew
		findViewById(R.id.second_btn).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    RecyclerActivity.class);
            startActivity(intent);
        });

		mNewsListView = findViewById(R.id.listview);
		// 模拟数据
		mockNews();
		mNewsListView.setAdapter(new NewsAdapter());

		// 初始化Colorful
		setupColorful();
	}

	/**
	 * 设置各个视图与颜色属性的关联
	 */
    private void setupColorful() {
		ViewGroupSetter listViewSetter = new ViewGroupSetter(mNewsListView);
		// 绑定ListView的Item View中的news_title视图，在换肤时修改它的text_color属性
		listViewSetter.childViewTextColor(R.id.news_title, R.attr.text_color);

		// 构建Colorful对象来绑定View与属性的对象关系
		mColorful = new Colorful.Builder(this)
				.backgroundDrawable(R.id.root_view, R.attr.root_view_bg)
				// 设置view的背景图片
				.backgroundColor(R.id.change_btn, R.attr.btn_bg)
				// 设置按钮文本颜色
				.textColor(R.id.change_btn, R.attr.text_color)
				// 设置背景色
				.textColor(R.id.textview, R.attr.text_color)
				// ImageView src
				.imageViewSrc(R.id.image_view, R.attr.my_image_view_src)
				// custom setter
				.setter(listViewSetter)
				.create();
	}

	/**
	 * 切换主题
	 */
	private void changeThemeWithColorful() {
		if (!isNight) {
			mColorful.setTheme(R.style.Colorful_NightTheme);
		} else {
			mColorful.setTheme(R.style.Colorful_DayTheme);
		}
		isNight = !isNight;
	}

	private void mockNews() {
		for (int i = 0; i < 20; i++) {
			mNewsList.add("News Title - " + i);
		}
	}

	/**
	 * 
	 * @author mrsimple
	 * 
	 */
	class NewsAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mNewsList.size();
		}

		@Override
		public String getItem(int position) {
			return mNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			NewsViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.news_lv_item, parent, false);
				viewHolder = new NewsViewHolder();
				viewHolder.newsTitleView = (TextView) convertView
						.findViewById(R.id.news_title);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (NewsViewHolder) convertView.getTag();
			}

			viewHolder.newsTitleView.setText(getItem(position));
			return convertView;
		}

	}

	public static class NewsViewHolder {
		public TextView newsTitleView;

	}

}
