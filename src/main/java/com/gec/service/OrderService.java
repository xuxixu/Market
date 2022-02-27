package com.gec.service;

import com.gec.bean.Orders;
import com.gec.bean.PageBean;

import java.util.List;

public interface OrderService {

	public List<Orders> findAll();
	
	public PageBean<Orders> findPage(Orders order, int pageIndex);

	public void update(Orders order);

	public void add(Orders order);

	public Orders findById(Integer id);

	public void delete(String[] id);
}
