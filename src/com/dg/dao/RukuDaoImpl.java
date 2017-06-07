package com.dg.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.dg.entity.Ruku;

public class RukuDaoImpl implements RukuDao {
	private SessionFactory sessionFactory;
	public void setsessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Ruku getRuku(String rid) {
		String hql = "from Ruku r where r.rid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, rid);
		return (Ruku)query.uniqueResult();
	}

	@Override
	public void addRuku(Ruku ruku) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(ruku);
	}

	@Override
	public boolean delRuku(String rid) {
		// TODO Auto-generated method stub
		String hql = "delete Ruku r where r.rid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, rid);
		
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateRuku(Ruku ruku) {
		// TODO Auto-generated method stub
		String rnum = ruku.getRnumber();
		String rpri = ruku.getRprice();
		String hql = "update Ruku r set r.rnumber=r.rnumber+"+rnum+",r.rprice=r.rprice+"+rpri+" where r.rid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, ruku.getRid());
		System.out.println(ruku.getDid());
		return (query.executeUpdate() > 0);
	}
	@Override
	public boolean findRuku(String rid) {
		// TODO Auto-generated method stub
		String hql = " from Ruku r where r.rid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, rid);
		List list = query.list();
		System.out.println(list.size());
		return (list.size()>0);
	}

}
