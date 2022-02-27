package com.gec.dao;

import com.gec.bean.OrderUtil;
import com.gec.bean.PageBean;

public interface ChartDao {

	public PageBean<OrderUtil> findPage(int sid,int year, int month,int day,int pageIndex);
	
	public OrderUtil findCountNum(int sid,int month,int day);
	
	public Double getYearTotal(int year,int month,int day, int sid);

	String getMaxName(int year, int month, int day, int sid);
}
