package com.gec.service.impl;

import com.gec.bean.Goods;
import com.gec.bean.PageBean;
import com.gec.dao.GoodsDao;
import com.gec.dao.impl.GoodsDaoImpl;
import com.gec.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	GoodsDao gd = new GoodsDaoImpl();
	@Override
	public PageBean<Goods> findPage(Goods goods, int pageIndex) {
		// TODO Auto-generated method stub
		return gd.findPage(goods, pageIndex);
	}

	@Override
	public Goods findById(int gid) {
		return gd.findById(gid);
	}

	@Override
	public void update(Goods goods) {
		gd.update(goods);
	}

	@Override
	public void save(Goods goods) {
		gd.add(goods);
	}

	@Override
	public void delete(String[] ids) {
		for(String i: ids)
		{
			gd.delete(Integer.parseInt(i));
		}
	}

}
