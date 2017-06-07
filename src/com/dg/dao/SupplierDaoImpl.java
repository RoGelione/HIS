package com.dg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.dg.entity.Supplier;

public class SupplierDaoImpl implements SupplierDao {
	private SessionFactory sessionFactory;
	public void setsessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Supplier getSupplier(String sid) {
		// TODO Auto-generated method stub
		String hql = "from Supplier s where s.sid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, sid);
		return (Supplier)query.uniqueResult();
	}

	@Override
	public void addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(supplier);
	}

	@Override
	public boolean delSupplier(String sid) {
		// TODO Auto-generated method stub
		String hql = "delete Supplier s where s.sid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, sid);
		
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		String hql = "update Supplier s set s.dvender = ?,s.saddress = ?,s.sphone = ? where s.sid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, supplier.getDvender());
		query.setString(1, supplier.getSaddress());
		query.setString(2, supplier.getSphone());
		query.setString(3, supplier.getSid());		
		return (query.executeUpdate() > 0);
	}
	@Override
	public boolean findSupplier(String dvender) {
		// TODO Auto-generated method stub
		String hql = " from Supplier s where s.dvender=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, dvender);
		List list = query.list();
		System.out.println(list.size());
		return (list.size()>0);
	}

}
