package com.gec.service;

import com.gec.bean.PageBean;
import com.gec.bean.Role;

import java.util.List;

public interface RoleService {

	public List<Role> findAll();
	
	public PageBean<Role> findPage(Role role,int pageIndex);

	public void update(Role role);

	public void add(Role role);

	public Role findById(Integer id);

	public void delete(String[] id);
}
