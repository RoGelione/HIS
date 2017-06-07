package com.dg.dao;

import com.dg.entity.Page;


public interface PageDao<T> {

	/**
	 * <b>function:</b> 传入查询语句和查询参数名key对应value，page指定currentPage和pageSize
	 * @param queryHql 查询语句
	 * @param paramMap 参数
	 * @param page 当前页和每页几条数据
	 * @throws Exception
	 */
	public Page<T> ShowPage(String queryHql,String countHql, int currentPage, int pageSize) throws Exception;

}