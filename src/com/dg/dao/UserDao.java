package com.dg.dao;

import java.util.List;

import com.dg.entity.User;

public interface UserDao {
	public User getUser(String uname);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String uname);
	
	public boolean updateUser(User user);
}
