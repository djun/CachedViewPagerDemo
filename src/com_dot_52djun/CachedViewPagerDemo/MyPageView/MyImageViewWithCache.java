//(c)Copyright.2014.DJun.2014-3-20 Project Created.
package com_dot_52djun.CachedViewPagerDemo.MyPageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyDataSource;

public class MyImageViewWithCache extends ImageView implements
		MyPageViewCacheController {

	private MyDataSource mDataSource;
	private boolean myResourceInitialed = false;
	private Bitmap noPictureBitmap;

	private class MyImageViewAsyncTask extends AsyncTask<Object, Void, Bitmap> {

		private MyImageViewWithCache iv;

		@Override
		protected Bitmap doInBackground(Object... params) {
			iv = (MyImageViewWithCache) params[0];
			MyDataSource dataSource = (MyDataSource) params[1];
			Bitmap b = (Bitmap) dataSource.get();
			return b;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				iv.setImageBitmap(result);
			}
		}

	}

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

	public Bitmap getNoPictureBitmap() {
		return noPictureBitmap;
	}

	public void setNoPictureBitmap(Bitmap noPictureBitmap) {
		this.noPictureBitmap = noPictureBitmap;
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
		if (mDataSource != null && !hasMyResourceInitialed()) {
			// normal way
			// Bitmap b = (Bitmap) mDataSource.get();
			// if (b != null) {
			// this.setImageBitmap(b);
			// }

			// task way
			MyImageViewAsyncTask task = new MyImageViewAsyncTask();
			task.execute(new Object[] { this, mDataSource });

			myResourceInitialed = true;
		}
	}

	@Override
	public synchronized void releaseMyResource() {
		this.setImageBitmap(noPictureBitmap);

		if (mDataSource != null && hasMyResourceInitialed()) {
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
		if (flag) {
			initMyResource();
		} else {
			releaseMyResource();
		}
	}

	@Override
	public boolean isMyPersistentCacheEnabled() {
		return this.myPersistentCacheEnabled;
	}

}
