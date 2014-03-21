package com_dot_52djun.CachedViewPagerDemo;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyFileUrlBitmapSource;
import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyRawBitmapSource;
import com_dot_52djun.CachedViewPagerDemo.MyPageView.MyImageViewWithCache;
import com_dot_52djun.CachedViewPagerDemo.MyViewPager.MyViewPager;

public class MainActivity extends Activity {

	private Context mContext;
	private MyViewPager mViewPager;

	private static final String PICS_FOLDER_NAME = "pics";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		this.mContext = this;
		// find views
		mViewPager = (MyViewPager) findViewById(R.id.viewpager);
		// pick pics in assets folder and put into my view pager
		try {
			String[] flLists = getAssets().list(PICS_FOLDER_NAME);
			for (String file : flLists) {
				// System.out.println("[picture loader]get pic name: " +
				// file);// debug
				if (needPickThisFile(file)) {
					String path = MyFileUrlBitmapSource.ASSETS_PATH_PREFIX
							+ PICS_FOLDER_NAME + "/" + file;
					MyImageViewWithCache iv = new MyImageViewWithCache(
							mContext, new MyFileUrlBitmapSource(mContext, path));
					mViewPager.addView(iv);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// pick pics in raw folder and put into my view pager
		for (int i = 1; i <= 5; ++i) {
			int rId = getResources().getIdentifier(
					"r" + String.valueOf(i) + ".jpg", "raw", getPackageName());
			if (rId != 0) {
				MyImageViewWithCache iv = new MyImageViewWithCache(mContext,
						new MyRawBitmapSource(mContext, rId));
				mViewPager.addView(iv);
			}
		}
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
