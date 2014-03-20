package com_dot_52djun.CachedViewPagerDemo.MyDataSource;

public interface MyDataSource {
	// get resource you need by this method. need "synchronized"
	public Object get();

	// drop resource you don't need any more by this method. need "synchronized"
	public void drop();
}
