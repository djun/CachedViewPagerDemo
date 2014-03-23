//(c)Copyright.2014.DJun.2014-3-20 Project Created.
package com_dot_52djun.CachedViewPagerDemo.MyPageView;

import com_dot_52djun.CachedViewPagerDemo.MyDataSource.MyDataSource;

public interface MyPageViewCacheController {

	// set my data source related to this controller
	public void setMyDataSource(MyDataSource source);

	// get my data source related to this controller
	public MyDataSource getMyDataSource();

	// use this method to pre-initial resources before show a page (with
	// MyDataSource recommended).
	// need "synchronized"
	public void initMyResource();

	// use this method to release resources when the resources in this page is
	// out of cache queue (with MyDataSource recommended).
	// need "synchronized"
	public void releaseMyResource();

	// get to know if my resource initialed
	public boolean hasMyResourceInitialed();

	// when this flag is set to enabled in a page, this page will be persistent
	// cache without being put into the cache queue.
	public void setEnableMyPersistentCache(boolean flag);

	// get the persistent cache flag.
	public boolean isMyPersistentCacheEnabled();
}
