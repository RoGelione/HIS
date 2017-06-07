package com.dg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.dg.entity.Drug;


public class DrugDaoImpl implements DrugDao {
	private SessionFactory sessionFactory;
	public void setsessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Drug getDrug(String did) {
		// TODO Auto-generated method stub
		String hql = "from Drug d where d.did=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, did);
		return (Drug)query.uniqueResult();
	}

	@Override
	public void addDrug(Drug drug) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(drug);
	}

	@Override
	public boolean delDrug(String did) {
		// TODO Auto-generated method stub
		String hql = "delete Drug d where d.did =?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, did);
		
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateDrug(Drug drug) {
		// TODO Auto-generated method stub
		String hql = "update Drug d set d.dname = ?,d.dcategory=?,d.dspec=?,d.dvender=?,d.date_begin=?,d.date_end=?,d.dprice=?,d.retail_price=? where d.did = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, drug.getDname());
		query.setString(1, drug.getDcategory());
		query.setString(2, drug.getDspec());
		query.setString(3, drug.getDvender());
		query.setString(4, drug.getDate_begin());
		query.setString(5, drug.getDate_end());
		query.setString(6, drug.getDprice());
		query.setString(7, drug.getRetail_price());
		query.setString(8, drug.getDid());	
		return (query.executeUpdate() > 0);
	}
	@Override
	public boolean findDrug(String did) {
		// TODO Auto-generated method stub
		String hql = " from Drug d where d.did=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, did);
		List list = query.list();
		System.out.println(list.size());
		return (list.size()>0);
	}

}
