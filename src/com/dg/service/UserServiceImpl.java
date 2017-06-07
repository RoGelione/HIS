package com.dg.service;

import java.io.IOException;
import java.util.List;

import com.dg.entity.Page;
import com.dg.entity.User;
import com.dg.Utils.StringUtil;
import com.dg.dao.PageDao;
import com.dg.dao.UserDao;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	private PageDao<User> pageDao;
	public void setPageDao(PageDao<User> pageDao) {
		this.pageDao= pageDao;
	}
	@Override
	public User getUser(String uname) {
		// TODO Auto-generated method stub
		
		return userDao.getUser(uname);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public boolean delUser(String uname) {
		// TODO Auto-generated method stub
		
		return userDao.delUser(uname);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
	@Override
	public Page<User> getUserList(int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<User> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer();  
		    try {  
		        sbHQL.append("from User u where 1=1 ");   
		        countHQL.append("select count(*) from User u where 1=1 ");   

		        sbHQL.append(" order by u.uname asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(), currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
	@Override
	public Page<User> getSuerList(String Search1,String Search2,String Search3, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Page<User> page = null;  
		    StringBuffer sbHQL = new StringBuffer();  
		    StringBuffer countHQL = new StringBuffer(); 
		    try {  
		    	sbHQL.append( "from User u where 1=1"); 
		        countHQL.append("select count(*) from User u where 1=1");
		        if (!StringUtil.isEmpty(Search1)) {
		        	sbHQL.append( " and u.uname like'%"+Search1+"%'"); 
			        countHQL.append(" and u.uname like'%"+Search1+"%'");      
		        } 
		        if(!StringUtil.isEmpty(Search2)){
		        	sbHQL.append( " and u.realname like'%"+Search2+"%'"); 
			        countHQL.append(" and u.realname like'%"+Search2+"%'");
		        }
		        if(!StringUtil.isEmpty(Search3)){
		        	sbHQL.append( " and u.flag like'%"+Search3+"%'"); 
			        countHQL.append(" and u.flag like'%"+Search3+"%'");
		        }
		        sbHQL.append(" order by u.uname asc ");  
		        page = pageDao.ShowPage(sbHQL.toString(), countHQL.toString(),  currentPage, pageSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}

}
