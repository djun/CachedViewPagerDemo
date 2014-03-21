package com_dot_52djun.CachedViewPagerDemo.MyPageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyDataSource;

public class MyImageViewWithCache extends ImageView implements
		MyPageViewCacheController {

	private MyDataSource mDataSource;
	private boolean myResourceInitialed = false;

	public MyImageViewWithCache(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyImageViewWithCache(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyImageViewWithCache(Context context) {
		super(context);
	}

	public MyImageViewWithCache(Context context, MyDataSource source) {
		super(context);
		this.mDataSource = source;
	}

	private boolean myPersistentCacheEnabled = false;

	public void setMyDataSource(MyDataSource source) {
		releaseMyResource();
		this.mDataSource = source;
		if (myPersistentCacheEnabled) {
			initMyResource();
		}
	}

	@Override
	public synchronized void initMyResource() {
		if (mDataSource != null) {
			Bitmap b = (Bitmap) mDataSource.get();
			this.setImageBitmap(b);
			myResourceInitialed = true;
		}
	}

	@Override
	public synchronized void releaseMyResource() {
		if (mDataSource != null) {
			mDataSource.drop();
			myResourceInitialed = false;
		}
	}

	@Override
	public boolean hasMyResourceInitialed() {
		return myResourceInitialed;
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
