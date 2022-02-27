package com.gec.service;

import java.util.List;

import com.gec.bean.Chart;
import com.gec.bean.OrderUtil;
import com.gec.bean.PageBean;
import com.gec.bean.Store;

public interface ChartService {

	public PageBean<OrderUtil> findPage(int sid,int year, int month,int day,int pageIndex);
	
	public int getTotalSales(int year,int month,int day,int sid);
	
	public OrderUtil findCountNum(int sid,int month,int day);
	
	public List<Chart> getYearTotal(int year, List<Store> sid);

	String getMaxName(int year, int month, int day, int sid);
}
