package com.dg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;


import com.dg.entity.Page;


public class PageDaoImpl<T> implements PageDao<T> {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override

	public Page<T> ShowPage(String queryHql, String countHql, int currentPage, int pageSize) throws Exception {
		Page<T> page = new Page<T>(currentPage, pageSize);
		try {
			int dataCount = queryForInt(countHql);/*数据记录总数*/
			System.out.println(dataCount);
			page.setResult(queryForList(queryHql, page.getOffset(),pageSize));
			page.setTotalsCount(dataCount); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}
	
	private int queryForInt(String queryIntHQL){
		Query query = sessionFactory.getCurrentSession().createQuery(queryIntHQL);
		
		int result = Integer.parseInt(query.uniqueResult().toString());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private List<T> queryForList(String queryHql, int offset,int pageSize){
		Query query = sessionFactory.getCurrentSession().createQuery(queryHql);
		
		//setQueryParameterValues(paramMap, query);
		if (offset>=0) {
			query.setFirstResult(offset);
		}
		if (pageSize>0) {
			query.setMaxResults(pageSize);/*设置sql语句的limit*/
		}		
		return query.list(); 
	}

}
