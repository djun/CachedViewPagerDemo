package com_dot_52djun.CachedViewPagerDemo.MyDataSource;

import java.io.IOException;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyFileUrlBitmapSource implements MyDataSource {

	// context
	private Context mContext;
	// specific resource url
	private String url;
	// bitmap cache for specific resource id
	private WeakReference<Bitmap> cache;
	// specific options used for decode the bitmap
	private BitmapFactory.Options opts;

	// assets prefix
	public static final String ASSETS_PATH_PREFIX = "file:///android_asset/";

	public MyFileUrlBitmapSource(Context context, String url) {
		this.mContext = context;
		this.url = url;
	}

	public MyFileUrlBitmapSource(Context context, String url,
			BitmapFactory.Options opts) {
		this.mContext = context;
		this.url = url;
		this.opts = opts;
	}

	@Override
	public synchronized Object get() {
		if (mContext == null || url == null) {
			return null;
		}

		if (cache == null || cache.get() == null) {
			// null? create it!
			AssetManager am = mContext.getAssets();
			Bitmap b;
			// url starts with http or https generally, ftp or else not
			// supported yet.
			if (url.startsWith(ASSETS_PATH_PREFIX)) {
				// from assets dir
				try {
					b = BitmapFactory
							.decodeStream(am.open(url
									.substring(ASSETS_PATH_PREFIX.length())),
									null, opts);
				} catch (IOException e) {
					b = null;
					e.printStackTrace();
				}
			} else {
				// from local file path
				// There maybe OOM for large image(low possibility). But for
				// better performance, we don't consider this yet.
				b = BitmapFactory.decodeFile(url, opts);
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
