package com.gec.service.impl;

import com.gec.bean.PageBean;
import com.gec.bean.Supplier;
import com.gec.dao.SupplierDao;
import com.gec.dao.impl.SupplierDaoImpl;
import com.gec.service.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

	SupplierDao sd = new SupplierDaoImpl();
	@Override
	public List<Supplier> findAll() {
		return sd.findAll();
	}
	@Override
	public PageBean<Supplier> findPage(Supplier Supplier, int pageIndex) {
		return sd.findPage(Supplier, pageIndex);
	}
	@Override
	public void update(Supplier Supplier) {
		sd.update(Supplier);
	}
	@Override
	public void add(Supplier Supplier) {
		sd.add(Supplier);
	}
	@Override
	public Supplier findById(Integer id) {
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
