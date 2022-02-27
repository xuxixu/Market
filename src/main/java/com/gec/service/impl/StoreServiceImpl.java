package com.gec.service.impl;

import com.gec.bean.PageBean;
import com.gec.bean.Store;
import com.gec.dao.StoreDao;
import com.gec.dao.impl.StoreDaoImpl;
import com.gec.service.StoreService;

import java.util.List;

public class StoreServiceImpl implements StoreService {

	StoreDao sd = new StoreDaoImpl();
	@Override
	public List<Store> findAll() {
		return sd.findAll();
	}
	@Override
	public PageBean<Store> findPage(Store store, int pageIndex) {
		return sd.findPage(store, pageIndex);
	}
	@Override
	public void update(Store store) {
		sd.update(store);
	}
	@Override
	public void add(Store store) {
		sd.add(store);
	}
	@Override
	public Store findById(Integer id) {
		return sd.findById(id);
	}
	@Override
	public void delete(String[] id) {
		for (String i:
				id) {
			sd.delete(Integer.parseInt(i));
		}

	}

}
