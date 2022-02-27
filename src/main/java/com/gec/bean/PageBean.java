package com.gec.bean;

import java.util.List;

public class PageBean<T> {

	private int totalCount; // 总数据记录数
	private int pageSize = 3; // 页面大小,初始为3
	private int pageCount; // 总页面数
	private int pageIndex; // 当前页面数
	private List<T> list; // 存储当前页面的数据

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//当总记录数发生改变时,影响总页数
		setPageCount();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setPageCount();
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount() {
		//通过计算得到总的页数
		this.pageCount = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize
				: this.totalCount / this.pageSize + 1;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
