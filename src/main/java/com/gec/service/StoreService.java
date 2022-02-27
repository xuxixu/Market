package com.gec.service;

import com.gec.bean.PageBean;
import com.gec.bean.Store;

import java.util.List;

public interface StoreService {

	public List<Store> findAll();
	
	public PageBean<Store> findPage(Store store,int pageIndex);

	public void update(Store store);

	public void add(Store store);

	public Store findById(Integer id);

	public void delete(String[] id);
}
