package com.gec.dao.impl;

import com.gec.bean.PageBean;
import com.gec.bean.Role;
import com.gec.bean.Supplier;
import com.gec.dao.SupplierDao;
import com.gec.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl extends DBUtil<Supplier> implements SupplierDao {

	@Override
	public List<Supplier> findAll() {
		// TODO Auto-generated method stub
		return query("select * from supplier");
	}

	@Override
	public Supplier findById(int id) {
		// TODO Auto-generated method stub
		return query("select * from supplier where sid=?", id).get(0);
	}

	@Override
	public PageBean<Supplier> findPage(Supplier entity, int pageIndex) {
		// 需要返回什么类型,就创建个什么类型的空对象
		PageBean<Supplier> pb = new PageBean<>();
		// 设置当前页
		pb.setPageIndex(pageIndex);
		String sql = "select * from supplier where 1=1 ";
		String sqlCount = "select count(*) from supplier where 1=1 ";
		// 创建一个用于存储参数的集合
		List<Object> obj = new ArrayList<>();
		// 通过判断对象中属性是否存在来拼接sql
		if (entity.getNumber() != null && !entity.getNumber().equals("")) {
			sql += "and number like ? ";
			sqlCount += "and number like ? ";
			obj.add("%" + entity.getNumber() + "%");
		}
		if (entity.getName() != null && !entity.getName().equals("")) {
			sql += "and name like ? ";
			sqlCount += "and name like ? ";
			obj.add("%" + entity.getName() + "%");
		}
		if (entity.getRemark() != null && !entity.getRemark().equals("")) {
			sql += "and remark like ? ";
			sqlCount += "and remark like ? ";
			obj.add("%" + entity.getRemark() + "%");
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
	public void add(Supplier entity) {
		// TODO Auto-generated method stub
		update("insert into supplier values(null,?,?,?)",entity.getNumber(),entity.getName(),entity.getRemark());
	}

	@Override
	public void update(Supplier entity) {
		// TODO Auto-generated method stub
		update("update supplier set number=?,name=?,remark=? where sid = ?",entity.getNumber(),entity.getName(),entity.getRemark(),entity.getSid());
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		update("delete from supplier where sid=?", id);

	}

	@Override
	public Supplier getEntity(ResultSet rs) throws Exception {
		Supplier sup = new Supplier();
		sup.setSid(rs.getInt(1));
		sup.setNumber(rs.getString(2));
		sup.setName(rs.getString(3));
		sup.setRemark(rs.getString(4));
		return sup;
	}

}
