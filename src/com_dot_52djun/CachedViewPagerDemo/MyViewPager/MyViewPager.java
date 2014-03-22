package com_dot_52djun.CachedViewPagerDemo.MyViewPager;

import java.util.LinkedList;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com_dot_52djun.CachedViewPagerDemo.MyPageView.MyPageViewCacheController;

public class MyViewPager extends ViewPager {

	// when a page view without persistent cache flag is cached, it will be put
	// into this queue. the queue will always keep 3 items in it and will drop
	// "old cache" when "new cache" comes.
	private LinkedList<MyPageViewCacheController> cachedViewsQueue;
	private static final int QUEUE_MAX_SIZE = 3;

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnPageChangeListener(lis);
	}

	public MyViewPager(Context context) {
		super(context);
		this.setOnPageChangeListener(lis);
	}

	private ViewPager.OnPageChangeListener lis = new ViewPager.OnPageChangeListener() {

		private boolean isAtRight = false;

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int position) {
			// TODO need "AsyncTask" to do these works instead
			System.out.println("MyViewPager: onPageSelected("
					+ String.valueOf(position) + ")");// debug

			checkPageView(getChildAt(position)); // check current view first
			if (position + 1 < getChildCount()) { // then right view
				checkPageView(getChildAt(position + 1));
			}
			if (position - 1 >= 0) { // then left view
				checkPageView(getChildAt(position - 1));
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		private void checkPageView(View view) {
			if (view instanceof MyPageViewCacheController) {
				MyPageViewCacheController c = (MyPageViewCacheController) view;
				if (!c.hasMyResourceInitialed()
						&& !c.isMyPersistentCacheEnabled()) {
					c.initMyResource();
					addAndCheckCacheQueue(c);
				}
			}
		}

		private void addAndCheckCacheQueue(MyPageViewCacheController controller) {
			if (cachedViewsQueue == null) {
				cachedViewsQueue = new LinkedList<MyPageViewCacheController>();
			}

			if (controller != null) {
				cachedViewsQueue.add(controller);
				if (cachedViewsQueue.size() > QUEUE_MAX_SIZE) {
					MyPageViewCacheController c = cachedViewsQueue.remove();
					c.releaseMyResource();
				}
			}
		}
	};

}
