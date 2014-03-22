//(c)Copyright.2014.DJun.2014-3-20 Project Created.
package com_dot_52djun.CachedViewPagerDemo.MyDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyRawBitmapSource implements MyDataSource {

	// context
	private Context mContext;
	// specific resource id
	private int rId;
	// bitmap cache for specific resource id
	private WeakReference<Bitmap> cache;
	// specific options used for decode the bitmap
	private BitmapFactory.Options opts;

	public MyRawBitmapSource(Context context, int resourceId) {
		this.mContext = context;
		this.rId = resourceId;
	}

	public MyRawBitmapSource(Context context, int resourceId,
			BitmapFactory.Options opts) {
		this.mContext = context;
		this.rId = resourceId;
		this.opts = opts;
	}

	@Override
	public synchronized Object get() {
		if (mContext == null || rId == 0) {
			return null;
		}

		if (cache == null || cache.get() == null) {
			// null? create it!
			Bitmap b = null;
			try {
				Resources res = mContext.getResources();
				InputStream is = res.openRawResource(rId);
				BitmapFactory.Options opts = this.opts;
				if (opts == null) {
					opts = new BitmapFactory.Options();
					opts.inPurgeable = true;
					opts.inInputShareable = true;
				}
				b = BitmapFactory.decodeStream(is, null, opts);

				is.close();
			} catch (IOException e) {
				b = null;
				e.printStackTrace();
			}

			if (b != null) {
				cache = new WeakReference<Bitmap>(b);
			}
		}

		return cache == null ? null : cache.get();
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
