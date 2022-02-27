package com.gec.service;

import com.gec.bean.Goods;
import com.gec.bean.PageBean;

public interface GoodsService {

	public PageBean<Goods> findPage(Goods goods,int pageIndex);

	Goods findById(int gid);

	void update(Goods goods);

	void save(Goods goods);

    void delete(String[] ids);
}
