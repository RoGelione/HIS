package com.dg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.dg.entity.Chuku;

public class ChukuDaoImpl implements ChukuDao {
	private SessionFactory sessionFactory;
	public void setsessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Chuku getChuku(String cid) {
		// TODO Auto-generated method stub
		String hql = "from Chuku c where c.cid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, cid);
		return (Chuku)query.uniqueResult();
	}

	@Override
	public void addChuku(Chuku chuku) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(chuku);
	}

	@Override
	public boolean updateChuku(Chuku chuku) {
		// TODO Auto-generated method stub
		String cnum = chuku.getCnumber();
		String cpri = chuku.getCprice();
		String hql = "update Chuku c set c.cnumber=c.cnumber+"+cnum+",c.cprice=c.cprice+"+cpri+" where c.cid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, chuku.getCid());
		return (query.executeUpdate() > 0);
	}
	@Override
	public boolean findChuku(String cid) {
		// TODO Auto-generated method stub
		String hql = " from Chuku c where c.cid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, cid);
		List list = query.list();
		System.out.println(list.size());
		return (list.size()>0);
	}
}
