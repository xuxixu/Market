package com.gec.service;

import com.gec.bean.PageBean;
import com.gec.bean.GoodsType;

import java.util.List;

public interface GoodsTypeService {

	public List<GoodsType> findAll();
	
	public PageBean<GoodsType> findPage(GoodsType goodsType,int pageIndex);

	public void update(GoodsType goodsType);

	public void add(GoodsType goodsType);

	public GoodsType findById(Integer id);

	public void delete(String[] id);
}
