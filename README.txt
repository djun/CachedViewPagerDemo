[CachedViewPager Demo] - DJun, 2014.3.20
	This demo is created by DJun, which can provide a ViewPager with resource
 used in every page limitedly cached (3 pages cached mechanism).

2014-3-23
1. Minor improvement.
2. I have tested this cache mechanism on BaseAdapter for Gallery and it shown a very good performance. I will make a demo by using Gallery with BaseAdapter at a later time.

2014-3-22
1. Update MyViewPager. Change implement of OnPageChangeListener in MyPageViewer to a private variable. Add MyImageViewAsyncTask class for loading resource. Fix some bugs.
2. Update MainActivity. Make it can show pictures smoothly.
3. The class "MyViewPager" extended from ViewPager may not be a very suitable demo for this cache mechanism, in here i just want to show all of you that how to apply the cache mechanism in your codes.

2014-3-21
1. Updated. Add some more Bitmap Source (from drawables, raw, or file url) implementing MyDataSource.
2. Use MyBitmapSource as a bitmap storage in MyImageViewWithCache.
3. Implement "3 pages cached mechanism" in MyViewPager.
4. Add some pics to "assets" and "raw" folder for testing.

2014-3-20
1. Project created.