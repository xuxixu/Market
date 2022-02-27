package com.gec.service;

import com.gec.bean.PageBean;
import com.gec.bean.User;

public interface UserService {

	public User login(String uname,String password);
	
	//带条件查询用户
	public PageBean<User> findEntity(User user,int pageIndex);
	
	public void add(User user);
	
	public User findById(int id);
	//通过账户查询
	public User findByNumber(String number);

	public void update(User user);

	public void delete(String id,String []ids);
}
