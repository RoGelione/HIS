package com.dg.entity;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int currentPage;// 当前页数	
	private int offset;// 记录偏移量	
	private int totalsPage;// 总页数
	private int pageSize;// 每页显示记录条数	
	private int totalsCount;// 总记录条数
	private List<T> result = new ArrayList<T>();// 查询返回结果
	private String url;// 分页链接
	
	public Page(){}
	
	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.offset = (currentPage-1)*pageSize;
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) throws Exception {
		if (currentPage < 0) {
			currentPage = 0;
		}
		this.currentPage = currentPage;
	}

	public int getTotalsPage() {
		try {
			if (totalsCount % pageSize == 0) {
				totalsPage = totalsCount / pageSize;
			} else {
				totalsPage = (totalsCount / pageSize) + 1;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return totalsPage;
	}

	public void setTotalsPage(int totalsPage) {
		if (totalsPage < 0) {
			totalsPage = 0;
		}
		this.totalsPage = totalsPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			pageSize = 20;
		}
		this.pageSize = pageSize;
	}

	public int getTotalsCount() {
		return totalsCount;
	}

	public void setTotalsCount(int totalsCount) {
		if (totalsCount < 0) {
			totalsCount = 0;
		}
		this.totalsCount = totalsCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
