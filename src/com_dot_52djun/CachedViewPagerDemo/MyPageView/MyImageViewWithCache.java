package com_dot_52djun.CachedViewPagerDemo.MyPageView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyImageViewWithCache extends ImageView implements
		MyPageViewCacheController {

	public MyImageViewWithCache(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyImageViewWithCache(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyImageViewWithCache(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private boolean myPersistentCacheEnabled = false;

	@Override
	public void initMyResource() {
		// TODO Auto-generated method stub
	}

	@Override
	public void releaseMyResource() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setEnableMyPersistentCache(boolean flag) {
		this.myPersistentCacheEnabled = flag;
	}

	@Override
	public boolean isMyPersistentCacheEnabled() {
		return this.myPersistentCacheEnabled;
	}

}
