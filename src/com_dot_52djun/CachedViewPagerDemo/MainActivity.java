//(c)Copyright.2014.DJun.2014-3-20 Project Created.
package com_dot_52djun.CachedViewPagerDemo;

import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyDataSource;
import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyFileUrlBitmapSource;
import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyRawBitmapSource;
import com_dot_52djun.CachedViewPagerDemo.MyPageView.MyImageViewWithCache;
import com_dot_52djun.CachedViewPagerDemo.MyViewPager.MyViewPager;

public class MainActivity extends Activity {

	private Context mContext;
	private MyViewPager mViewPager;

	private static final String PICS_FOLDER_NAME = "pics";

	// this Adapter class is for testing MyPageViewer
	private class MyPagerAdapter extends PagerAdapter {

		private Context context;
		private ArrayList<MyDataSource> mdsList;

		public MyPagerAdapter(Context context, ArrayList<MyDataSource> list) {
			this.context = context;
			this.mdsList = list;
		}

		@Override
		public int getCount() {
			return mdsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			MyImageViewWithCache iv = new MyImageViewWithCache(context,
					mdsList.get(position));
			container.addView(iv);
			return iv;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Picture " + String.valueOf(position + 1);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		this.mContext = this;
		// find views
		mViewPager = (MyViewPager) findViewById(R.id.viewpager);
		// will put pics into an ArrayList
		ArrayList<MyDataSource> mdsList = new ArrayList<MyDataSource>();
		// pick pics in assets
		try {
			String[] flLists = getAssets().list(PICS_FOLDER_NAME);
			for (String file : flLists) {
				if (needPickThisFile(file)) {
					String path = MyFileUrlBitmapSource.ASSETS_PATH_PREFIX
							+ PICS_FOLDER_NAME + "/" + file;
					mdsList.add(new MyFileUrlBitmapSource(mContext, path));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// pick pics in raw folder
		for (int i = 1; i <= 5; ++i) {
			int rId = getResources().getIdentifier("r" + String.valueOf(i),
					"raw", getPackageName());
			if (rId != 0) {
				mdsList.add(new MyRawBitmapSource(mContext, rId));
			}
		}
		// make and set adapter
		MyPagerAdapter adapter = new MyPagerAdapter(mContext, mdsList);
		mViewPager.setAdapter(adapter);
	}

	@SuppressLint("DefaultLocale")
	private static boolean needPickThisFile(String fileName) {
		String fn = fileName.toLowerCase();
		return fn.endsWith(".jpg") || fn.endsWith(".png");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
