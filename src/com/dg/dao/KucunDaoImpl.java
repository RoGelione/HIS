package com.dg.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.dg.entity.Kucun;

public class KucunDaoImpl implements KucunDao {
	private SessionFactory sessionFactory;
	public void setsessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Kucun getKucun(String kid) {
		// TODO Auto-generated method stub
		String hql = "from Kucun k where k.kid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, kid);
		return (Kucun)query.uniqueResult();
	}
	@Override
	public String getKnum(String did) {
		// TODO Auto-generated method stub
		String hql = "select knumber from Kucun k where k.did=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, did);
		return (String) query.uniqueResult();
	}
	@Override
	public boolean delKucun(String kid) {
		// TODO Auto-generated method stub
		String hql = "delete Kucun k where k.kid =?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, kid);
		
		return (query.executeUpdate() > 0);
	}


}
