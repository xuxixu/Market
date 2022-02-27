package com.gec.dao.impl;

import com.gec.bean.GoodsType;
import com.gec.bean.PageBean;
import com.gec.bean.Role;
import com.gec.dao.GoodsTypeDao;
import com.gec.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GoodsTypeDaoImpl extends DBUtil<GoodsType> implements GoodsTypeDao {

	@Override
	public List<GoodsType> findAll() {
		return query("select * from goodstype");
	}

	@Override
	public GoodsType findById(int id) {
		return query("select * from goodstype where tid=?", id).get(0);
	}

	@Override
	public PageBean<GoodsType> findPage(GoodsType entity, int pageIndex) {
		// TODO Auto-generated method stub
		// 需要返回什么类型,就创建个什么类型的空对象
		PageBean<GoodsType> pb = new PageBean<>();
		// 设置当前页
		pb.setPageIndex(pageIndex);
		String sql = "select * from goodstype where 1=1 ";
		String sqlCount = "select count(*) from goodstype where 1=1 ";
		// 创建一个用于存储参数的集合
		List<Object> obj = new ArrayList<>();
		// 通过判断对象中属性是否存在来拼接sql
		if (entity.getTname() != null && !entity.getTname().equals("")) {
			sql += "and tname like ? ";
			sqlCount += "and tname like ? ";
			obj.add("%" + entity.getTname() + "%");
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
	public void add(GoodsType entity) {
		// TODO Auto-generated method stub
		update("insert into goodstype values(null,?,?)",entity.getTname(),entity.getRemark());
	}

	@Override
	public void update(GoodsType entity) {
		// TODO Auto-generated method stub
		update("update goodstype set tname=?,remark=? where tid = ?",entity.getTname(),entity.getRemark(),entity.getTid());
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		update("delete from goodstype where tid=?", id);
	}

	@Override
	public GoodsType getEntity(ResultSet rs) throws Exception {
		GoodsType gt = new GoodsType();
		gt.setTid(rs.getInt(1));
		gt.setTname(rs.getString(2));
		gt.setRemark(rs.getString(3));
		return gt;
	}

}
