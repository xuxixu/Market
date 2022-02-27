package com.gec.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gec.bean.Chart;
import com.gec.bean.OrderUtil;
import com.gec.bean.PageBean;
import com.gec.bean.Store;
import com.gec.dao.ChartDao;
import com.gec.dao.impl.ChartDaoImpl;
import com.gec.service.ChartService;

public class ChartServiceImpl implements ChartService {

	ChartDao cd = new ChartDaoImpl();
	@Override
	public PageBean<OrderUtil> findPage(int sid,int year, int month, int day, int pageIndex) {
		return cd.findPage(sid,year, month, day, pageIndex);
	}
	
	@Override
	public int getTotalSales(int year, int month, int day, int sid) {
		double total = cd.getYearTotal(year,month, day, sid);
		return (int)total;
	}

	@Override
	public OrderUtil findCountNum(int sid, int month, int day) {
		// TODO Auto-generated method stub
		return cd.findCountNum(sid, month, day);
	}
	@Override
	public List<Chart> getYearTotal(int year, List<Store> sid) {
		List<Chart> list = new ArrayList<>();
		for (Store s : sid) {
			Chart c = new Chart();
			c.setName(s.getName());
			//循环本年的12个月营业额
			for (int i = 1; i <= 12; i++) {
				c.getDate().add(cd.getYearTotal(year, i, 0, s.getId()));
			}
			list.add(c);
		}

		return list;
	}

	@Override
	public String getMaxName(int year, int month, int day, int sid) {
		return cd.getMaxName(year,month, day, sid);
	}
}
