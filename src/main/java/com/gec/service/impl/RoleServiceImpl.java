package com.gec.service.impl;

import com.gec.bean.PageBean;
import com.gec.bean.Role;
import com.gec.dao.RoleDao;
import com.gec.dao.impl.RoleDaoImpl;
import com.gec.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

	RoleDao rd = new RoleDaoImpl();
	@Override
	public List<Role> findAll() {
		return rd.findAll();
	}
	@Override
	public PageBean<Role> findPage(Role role, int pageIndex) {
		return rd.findPage(role, pageIndex);
	}
	@Override
	public void update(Role role) {
		rd.update(role);
	}
	@Override
	public void add(Role role) {
		rd.add(role);
	}
	@Override
	public Role findById(Integer id) {
		return rd.findById(id);
	}
	@Override
	public void delete(String[] id) {
		for (String i:
				id) {
			rd.delete(Integer.parseInt(i));
		}

	}

}
