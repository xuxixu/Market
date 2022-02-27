package com.gec.service.impl;

import com.gec.bean.Orders;
import com.gec.bean.PageBean;
import com.gec.dao.OrderDao;
import com.gec.dao.impl.OrderDaoImpl;
import com.gec.service.OrderService;


import java.util.List;

public class OrderServiceImpl implements OrderService {

	OrderDao od = new OrderDaoImpl();
	@Override
	public List<Orders> findAll() {
		return od.findAll();
	}
	@Override
	public PageBean<Orders> findPage(Orders order, int pageIndex) {
		return od.findPage(order, pageIndex);
	}
	@Override
	public void update(Orders order) {
		od.update(order);
	}
	@Override
	public void add(Orders orders) {
		od.add(orders);
	}
	@Override
	public Orders findById(Integer id) {
		return od.findById(id);
	}
	@Override
	public void delete(String[] id) {
		for (String i:
				id) {
			od.delete(Integer.parseInt(i));
		}

	}

}
