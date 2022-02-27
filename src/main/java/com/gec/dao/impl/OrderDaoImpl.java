package com.gec.dao.impl;

import com.gec.bean.Orders;
import com.gec.bean.PageBean;
import com.gec.dao.*;
import com.gec.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends DBUtil<Orders> implements OrderDao {

	UserDao ud = new UserDaoImpl();
	GoodsDao gd = new GoodsDaoImpl();
	StoreDao sd = new StoreDaoImpl();
	@Override
	public List<Orders> findAll() {
		// TODO Auto-generated method stub
		return query("select * from order");
	}

	@Override
	public Orders findById(int id) {
		return query("select * from order where gid=?", id).get(0);
	}

	@Override
	public PageBean<Orders> findPage(Orders entity, int pageIndex) {
		//需要返回什么类型,就创建个什么类型的空对象
				PageBean<Orders> pb = new PageBean<>();
				//设置当前页
				pb.setPageIndex(pageIndex);
				String sql = "select * from order where 1=1 ";
				String sqlCount = "select count(*) from order where 1=1 ";
				//创建一个用于存储参数的集合
				List<Object> obj = new ArrayList<>();
				//通过判断对象中属性是否存在来拼接sql
				if(entity.getUid() > 0){
					sql += "and uid = ? ";
					sqlCount +="and uid = ? ";
					obj.add("%"+entity.getUid()+"%");
				}
				if(entity.getGid()>0){
					sql += "and gid=? ";
					sqlCount += "and gid=? ";
					obj.add(entity.getGid());
				}
				if(entity.getSid() > 0){
					sql +="and sid = ? ";
					sqlCount +="and sid = ? ";
					obj.add("%"+entity.getSid()+"%");
				}
				if(entity.getBnum()>0){
					sql += "and bnum=?";
					sqlCount += "and bnum=?";
					obj.add(entity.getBnum());
				}
				if(entity.getSubtotal()>0){
					sql += "and subtotal=?";
					sqlCount += "and subtotal=?";
					obj.add(entity.getSubtotal());
				}

		//查询出总记录数,并存入pb对象中
				pb.setTotalCount(getCount(sqlCount, obj.toArray()));
				//页面查询数据,需要拼接limit字段
				sql +=" limit ?,?";
				//-- 第一个参数表示为每一页的起始行下标,第二参数为页面大小
				//-- 起始行的公式为: (pageIndex-1)*pageSize
				obj.add((pageIndex-1)*pb.getPageSize());
				obj.add(pb.getPageSize());
				//将参数集合转换为数组
				pb.setList(query(sql, obj.toArray()));
				return pb;
	}

	@Override
	public void add(Orders entity) {
		// TODO Auto-generated method stub
		update("update order set uid=?, gid=?, sid=?,buydate=?,bnum=?,subtotal=? where gid = ",entity.getUid(),entity.getGid(),entity.getSid(),entity.getBuydate(),entity.getBnum(),entity.getSubtotal(),entity.getOid());
	}

	@Override
	public void update(Orders entity) {
		// TODO Auto-generated method stub
		update("insert into order values(null,?,?,?,?,?,?)",entity.getUid(),entity.getGid(),entity.getSid(),entity.getBuydate(),entity.getBnum(),entity.getSubtotal());
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		update("delete from order where oid=?", id);
	}

	@Override
	public Orders getEntity(ResultSet rs) throws Exception {
		Orders g = new Orders();
		g.setOid(rs.getInt(1));
		g.setUid(rs.getInt(2));
		g.setUser(ud.findById(rs.getInt(2)));
		g.setGid(rs.getInt(3));
		g.setGoods(gd.findById(rs.getInt(3)));
		g.setSid(rs.getInt(4));
		g.setStore(sd.findById(rs.getInt(4)));
		g.setBuydate(rs.getDate(5));
		g.setBnum(rs.getInt(6));
		g.setSubtotal(rs.getDouble(7));
		return g;
	}

}
