package com.gec.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.bean.OrderUtil;
import com.gec.bean.PageBean;
import com.gec.dao.ChartDao;
import com.gec.dao.GoodsDao;
import com.gec.util.DBUtil;

public class ChartDaoImpl extends DBUtil<OrderUtil> implements ChartDao {

	GoodsDao gd = new GoodsDaoImpl();
	@Override
	public PageBean<OrderUtil> findPage(int sid, int year, int month, int day, int pageIndex) {
		PageBean<OrderUtil> pb = new PageBean<>();
		pb.setPageIndex(pageIndex);
		String sql = "select gid,sum(bnum) num,sum(subtotal) from orders where 1=1 ";
		String sqlCount = "select count(distinct gid) from orders where 1=1 ";
		List<Object> obj = new ArrayList<>();
		if(sid>0){
			sql += "and sid=? ";
			sqlCount += "and sid=? ";
			obj.add(sid);
		}
		if(year>0){
			sql += "and year(buydate)=? ";
			sqlCount += "and year(buydate)=? ";
			obj.add(year);
		}
		if(month>0){
			sql += "and month(buydate)=? ";
			sqlCount += "and month(buydate)=? ";
			obj.add(month);
		}
		if(day>0){
			sql += "and dayofmonth(buydate)=? ";
			sqlCount += "and dayofmonth(buydate)=? ";
			obj.add(day);
		}
		pb.setTotalCount(getCount(sqlCount, obj.toArray()));
		sql += " group by gid order by num desc limit ?,?";
		obj.add((pageIndex-1)*pb.getPageSize());
		obj.add(pb.getPageSize());
		pb.setList(query(sql, obj.toArray()));
		return pb;
	}

	@Override
	public OrderUtil findCountNum(int sid,int month,int day) {
		String sql = "select gid,sum(bnum) bnums,sum(subtotal) total from orders where 1=1 ";
		List<Object> obj = new ArrayList<>();
		if(sid>0){
			sql += "and sid=? ";
			obj.add(sid);
		}
		if(month>0){
			sql += "and month(buydate)=? and year(buydate)=2022 ";
			obj.add(month);
		}
		if(day>0){
			sql += "and dayofmonth(buydate)=? ";
			obj.add(day);
		}
		sql += " gorup by gid order by bnums desc,total desc";
		List<OrderUtil> list = query(sql, obj.toArray());
		return list.get(0);
	}

	@Override
	public Double getYearTotal(int year, int month, int day, int sid){
		List<Object> obj = new ArrayList<>(); //用于存储sql参数
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String sql = "select sum(subtotal) total from orders where 1=1 ";
			
			if(sid>0){
				sql += "and sid=? ";
				obj.add(sid);
			}
			if(year>0){
				sql += "and year(buydate)=? ";
				obj.add(year);
			}
			if(month>0){
				sql += "and month(buydate)=? ";
				obj.add(month);
			}
			if(day>0){
				sql += "and dayofmonth(buydate)=?";
				obj.add(day);
			}
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < obj.size(); i++) {
				pst.setObject(i+1, obj.get(i));
			}
			rs = pst.executeQuery();
			if(rs.next()){
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose(rs, pst, conn);
		}
		return 0.0;
	}

	@Override
	public String getMaxName(int year, int month, int day, int sid) {
		String sql = "select gid,sum(bnum) num,sum(subtotal) from orders where 1=1 ";
		String sqlCount = "select count(distinct gid) from orders where 1=1 ";
		List<Object> obj = new ArrayList<>();
		if(sid>0){
			sql += "and sid=? ";
			sqlCount += "and sid=? ";
			obj.add(sid);
		}
		if(year>0){
			sql += "and year(buydate)=? ";
			sqlCount += "and year(buydate)=? ";
			obj.add(year);
		}
		if(month>0){
			sql += "and month(buydate)=? ";
			sqlCount += "and month(buydate)=? ";
			obj.add(month);
		}
		if(day>0){
			sql += "and dayofmonth(buydate)=? ";
			sqlCount += "and dayofmonth(buydate)=? ";
			obj.add(day);
		}
		sql += " group by gid order by num desc";
		List<OrderUtil> query = query(sql, obj.toArray());
		return query.get(0).getGoods().getName();
	}

	@Override
	public OrderUtil getEntity(ResultSet rs) throws Exception {
		OrderUtil ou = new OrderUtil();
		ou.setGoods(gd.findById(rs.getInt(1)));
		ou.setSalesNum(rs.getInt(2));
		ou.setSalesMoney(rs.getDouble(3));
		return ou;
	}
}
