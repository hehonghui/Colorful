package com.example.androidthemedemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simple.colorful.Colorful;
import com.simple.colorful.setter.ViewGroupSetter;

/**
 * 演示RecyclerView的换肤效果
 * @author mrsimple
 *
 */
public class RecyclerActivity extends Activity {

	RecyclerView mRecyclerView;
	List<String> mNewsList = new ArrayList<String>();
	Colorful mColorful;
	boolean isNight = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTheme(R.style.NightTheme);

		setContentView(R.layout.activity_recycler);

		findViewById(R.id.change_btn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeThemeWithColorful();
			}
		});

		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemViewCacheSize(0);
		// 模拟数据
		mockNews();
		mRecyclerView.setAdapter(new NewsAdapter());
		
		// 初始化Colorful
		setupColorful();
	}

	/**
	 * 设置各个视图与颜色属性的关联
	 */
	private void setupColorful() {
		ViewGroupSetter recyclerViewSetter = new ViewGroupSetter(mRecyclerView,
				0);
		recyclerViewSetter.childViewTextColor(R.id.news_title,
				R.attr.text_color);

		mColorful = new Colorful.Builder(this)
				.backgroundColor(R.id.change_btn, R.attr.btn_bg) // 设置背景色
				.setter(recyclerViewSetter) // 手动设置setter
				.create(); // 设置文本颜色
	}

	private void changeThemeWithColorful() {
		if (!isNight) {
			mColorful.setTheme(R.style.DayTheme);
		} else {
			mColorful.setTheme(R.style.NightTheme);
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
	class NewsAdapter extends Adapter<NewsViewHolder> {

		@Override
		public int getItemCount() {
			return mNewsList.size();
		}

		public String getItem(int position) {
			return mNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View itemView = LayoutInflater.from(RecyclerActivity.this).inflate(
					R.layout.news_lv_item, parent, false);
			return new NewsViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(NewsViewHolder viewHolder, int position) {
			viewHolder.newsTitleView.setText(getItem(position));
		}
	}

	public static class NewsViewHolder extends ViewHolder {

		public TextView newsTitleView;

		public NewsViewHolder(View itemView) {
			super(itemView);

			newsTitleView = (TextView) itemView.findViewById(R.id.news_title);
		}
	}
}
