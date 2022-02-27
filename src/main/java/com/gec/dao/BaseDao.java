package com.gec.dao;

import com.gec.bean.PageBean;

import java.util.List;

public interface BaseDao<T> {

	public List<T> findAll();

	public T findById(int id);
	/**
	 * 分页查询,如果对象为null则表示查询所有分页,如果有条件则带条件
	 */
	public PageBean<T> findPage(T entity,int pageIndex);
	//添加角色
	public void add(T entity);
	//修改角色
	public void update(T entity);
	//删除角色
	public void delete(int id);
}
