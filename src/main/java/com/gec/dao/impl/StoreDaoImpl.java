package com.gec.dao.impl;

import com.gec.bean.PageBean;
import com.gec.bean.Store;
import com.gec.dao.StoreDao;
import com.gec.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoImpl extends DBUtil<Store> implements StoreDao {

	@Override
	public List<Store> findAll() {
		return query("select * from store");
	}

	@Override
	public Store findById(int id) {
		return query("select * from store where id=?", id).get(0);
	}

	@Override
	public PageBean<Store> findPage(Store store, int pageIndex) {
		// 需要返回什么类型,就创建个什么类型的空对象
		PageBean<Store> pb = new PageBean<>();
		// 设置当前页
		pb.setPageIndex(pageIndex);
		String sql = "select * from store where 1=1 ";
		String sqlCount = "select count(*) from store where 1=1 ";
		// 创建一个用于存储参数的集合
		List<Object> obj = new ArrayList<>();
		// 通过判断对象中属性是否存在来拼接sql
		if (store.getNumber() != null && !store.getNumber().equals("")) {
			sql += "and number like ? ";
			sqlCount += "and number like ? ";
			obj.add("%" + store.getNumber() + "%");
		}
		if (store.getRemark() != null && !store.getRemark().equals("")) {
			sql += "and remark like ? ";
			sqlCount += "and remark like ? ";
			obj.add("%" + store.getRemark() + "%");
		}
		if (store.getName() != null && !store.getName().equals("")) {
			sql += "and name like ? ";
			sqlCount += "and name like ? ";
			obj.add("%" + store.getName() + "%");
		}
		if (store.getLoc() != null && !store.getLoc().equals("")) {
			sql += "and loc like ? ";
			sqlCount += "and loc like ? ";
			obj.add("%" + store.getLoc() + "%");
		}
		// 查询出总记录数,并存入pb对象中
		pb.setTotalCount(getCount(sqlCount, obj.toArray()));
		// 页面查询数据,需要拼接limit字段
		sql += " limit ?,?";
		// -- 第一个参数表示为每一页的起始行下标,第二参数为页面大小
		// -- 起始行的公式为: (pageIndex-1)*pageSize
		obj.add((pageIndex - 1) * pb.getPageSize());
		obj.add(pb.getPageSize());
		// 将参数集合转换为数组
		pb.setList(query(sql, obj.toArray()));
		return pb;
	}

	@Override
	public void add(Store store) {
		update("insert into store values(null,?,?,?,?)", store.getNumber(),store.getName(),store.getLoc(),store.getRemark());
	}

	@Override
	public void update(Store store) {
		update("update store set number=?,name=?,remark=? where id=?", store.getNumber(),store.getName(),store.getRemark(),store.getId());
	}

	@Override
	public void delete(int id) {
		update("delete from store where id=?", id);
	}

	@Override
	public Store getEntity(ResultSet rs) throws Exception {
		Store store = new Store();
		store.setId(rs.getInt(1));
		store.setNumber(rs.getString(2));
		store.setName(rs.getString(3));
		store.setLoc(rs.getString(4));
		store.setRemark(rs.getString(5));
		return store;
	}
}
