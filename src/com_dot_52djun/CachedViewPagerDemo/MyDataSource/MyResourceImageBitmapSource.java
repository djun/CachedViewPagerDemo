package com_dot_52djun.CachedViewPagerDemo.MyDataSource;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyResourceImageBitmapSource implements MyDataSource {

	// specific resource id
	private int rId;
	// bitmap cache for specific resource id
	private WeakReference<Bitmap> cache;
	// specific options used for decode the bitmap
	private BitmapFactory.Options opts;

	public MyResourceImageBitmapSource(int resourceId) {
		this.rId = resourceId;
	}

	public MyResourceImageBitmapSource(int resourceId,
			BitmapFactory.Options opts) {
		this.rId = resourceId;
		this.opts = opts;
	}

	@Override
	public synchronized Object get() {
		if (cache == null || cache.get() == null) {
			// TODO create it!
		}

		return cache.get();
	}

	@Override
	public synchronized void drop() {
		if (cache != null) {
			Bitmap b = cache.get();
			if (b != null) {
				b.recycle();
			}

			cache = null;
		}
	}

}
