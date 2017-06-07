package com.dg.service;

import java.util.List;

import com.dg.entity.User;
import com.dg.entity.Page;

public interface UserService {
	public User getUser(String uname);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String uname);
	
	public boolean updateUser(User user);
	
	public Page<User> getUserList(int currentPage,int pageSize) throws Exception;
	
	public Page<User> getSuerList(String Search1,String Search2,String Search3,int currentPage,int pageSize) throws Exception;
}
