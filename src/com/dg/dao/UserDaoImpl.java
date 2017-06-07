package com.dg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.dg.dao.UserDao;
import com.dg.entity.User;

public class UserDaoImpl implements UserDao{
	private SessionFactory sessionFactory;
	public void setsessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public User getUser(String uname) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.uname=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, uname);
		return (User)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public boolean delUser(String uname) {
		// TODO Auto-generated method stub
		String hql = "delete User u where u.uname = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, uname);
		
		return (query.executeUpdate() > 0);
		
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		String hql = "update User u set u.uname = ?,u.upassword = ?,u.flag = ?,u.uphone = ? where u.realname = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getUname());
		query.setString(1, user.getUpassword());
		query.setString(2, user.getFlag());
		query.setString(3, user.getUphone());
		query.setString(4, user.getRealname());		
		return (query.executeUpdate() > 0);
	}

}
