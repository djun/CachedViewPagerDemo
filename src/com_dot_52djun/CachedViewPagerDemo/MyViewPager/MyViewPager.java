//(c)Copyright.2014.DJun.2014-3-20 Project Created.
package com_dot_52djun.CachedViewPagerDemo.MyViewPager;

import java.util.LinkedList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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

	@Override
	public void setAdapter(PagerAdapter arg0) {
		super.setAdapter(arg0);
	}

	private ViewPager.OnPageChangeListener lis = new ViewPager.OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int position) {
			System.out.println("page count=" + getChildCount() + ",position="
					+ position);// debug

			// TODO need "AsyncTask" to do these works instead
			// check current view first
			checkPageView(getChildAt(position), true);
			// then right view
			if (position + 1 < getChildCount()) {
				checkPageView(getChildAt(position + 1), true);
			}
			// then left view
			if (position - 1 >= 0) {
				checkPageView(getChildAt(position - 1), false);
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		private void checkPageView(View view, boolean isAtRight) {
			if (view instanceof MyPageViewCacheController) {
				MyPageViewCacheController c = (MyPageViewCacheController) view;
				if (!c.hasMyResourceInitialed()
						&& !c.isMyPersistentCacheEnabled()) {
					c.initMyResource();
					addAndCheckCacheQueue(c, isAtRight);
				}
			}
		}

		private void addAndCheckCacheQueue(
				MyPageViewCacheController controller, boolean isAtRight) {
			if (cachedViewsQueue == null) {
				cachedViewsQueue = new LinkedList<MyPageViewCacheController>();
			}

			if (controller != null) {
				if (isAtRight) {
					cachedViewsQueue.addLast(controller);
					if (cachedViewsQueue.size() > QUEUE_MAX_SIZE) {
						MyPageViewCacheController c = cachedViewsQueue
								.removeFirst();
						c.releaseMyResource();
					}
				} else {
					cachedViewsQueue.addFirst(controller);
					if (cachedViewsQueue.size() > QUEUE_MAX_SIZE) {
						MyPageViewCacheController c = cachedViewsQueue
								.removeLast();
						c.releaseMyResource();
					}
				}
			}
		}
	};

}
