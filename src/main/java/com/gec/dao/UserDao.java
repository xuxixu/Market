package com.gec.dao;

import com.gec.bean.User;

public interface UserDao extends BaseDao<User> {
	
	public User login(String number, String password);
	
	public User checkNumber(String number);
}
