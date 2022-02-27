package com.gec.service.impl;

import com.gec.bean.PageBean;
import com.gec.bean.GoodsType;
import com.gec.dao.GoodsTypeDao;
import com.gec.dao.impl.GoodsTypeDaoImpl;
import com.gec.service.GoodsTypeService;

import java.util.List;

public class GoodsTypeServiceImpl implements GoodsTypeService {

	GoodsTypeDao rd = new GoodsTypeDaoImpl();
	@Override
	public List<GoodsType> findAll() {
		return rd.findAll();
	}
	@Override
	public PageBean<GoodsType> findPage(GoodsType goodsType, int pageIndex) {
		return rd.findPage(goodsType, pageIndex);
	}
	@Override
	public void update(GoodsType goodsType) {
		rd.update(goodsType);
	}
	@Override
	public void add(GoodsType goodsType) {
		rd.add(goodsType);
	}
	@Override
	public GoodsType findById(Integer id) {
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
