package com.gec.service.impl;

import com.gec.bean.PageBean;
import com.gec.bean.User;
import com.gec.dao.UserDao;
import com.gec.dao.impl.UserDaoImpl;
import com.gec.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao ed = new UserDaoImpl();
	@Override
	public User login(String uname, String password) {
		// TODO Auto-generated method stub
		return ed.login(uname, password);
	}

	@Override
	public PageBean<User> findEntity(User user,int pageIndex) {
		// TODO Auto-generated method stub
		return ed.findPage(user, pageIndex);
	}
	@Override
	public void add(User user) {
		ed.add(user);
	}
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return ed.findById(id);
	}
	@Override
	public void update(User user) {
		ed.update(user);
	}

	@Override
	public void delete(String id,String []ids) {
		for (String uid: ids) {
			if(id.equals(id))
				continue;
			ed.delete(Integer.parseInt(uid));
		}
	}

	@Override
	public User findByNumber(String number) {
		// TODO Auto-generated method stub
		return ed.checkNumber(number);
	}
}
