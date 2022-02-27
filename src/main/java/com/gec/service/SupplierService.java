package com.gec.service;

import com.gec.bean.PageBean;
import com.gec.bean.Supplier;

import java.util.List;

public interface SupplierService {

	public List<Supplier> findAll();
	
	public PageBean<Supplier> findPage(Supplier supplier,int pageIndex);

	public void update(Supplier supplier);

	public void add(Supplier supplier);

	public Supplier findById(Integer id);

	public void delete(String[] id);
}
