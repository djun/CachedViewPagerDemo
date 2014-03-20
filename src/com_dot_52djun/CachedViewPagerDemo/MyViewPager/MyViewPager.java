package com_dot_52djun.CachedViewPagerDemo.MyViewPager;

import java.util.LinkedList;

import com_dot_52djun.CachedViewPagerDemo.MyPageView.MyImageViewWithCache;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class MyViewPager extends ViewPager {

	// when a page view without persistent cache flag is cached, it will be put
	// into this queue. the queue will always keep 3 items in it and will drop
	// "old cache" when "new cache" comes.
	private LinkedList<MyImageViewWithCache> cachedViewsQueue;

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

}
