package com.gec.dao.impl;

import com.gec.bean.Goods;
import com.gec.bean.PageBean;
import com.gec.dao.GoodsDao;
import com.gec.dao.GoodsTypeDao;
import com.gec.dao.SupplierDao;
import com.gec.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl extends DBUtil<Goods> implements GoodsDao {

	GoodsTypeDao gtd = new GoodsTypeDaoImpl();
	SupplierDao sd = new SupplierDaoImpl();
	@Override
	public List<Goods> findAll() {
		// TODO Auto-generated method stub
		return query("select * from goods");
	}

	@Override
	public Goods findById(int id) {
		return query("select * from goods where gid=?", id).get(0);
	}

	@Override
	public PageBean<Goods> findPage(Goods entity, int pageIndex) {
		//需要返回什么类型,就创建个什么类型的空对象
				PageBean<Goods> pb = new PageBean<>();
				//设置当前页
				pb.setPageIndex(pageIndex);
				String sql = "select * from goods where 1=1 ";
				String sqlCount = "select count(*) from goods where 1=1 ";
				//创建一个用于存储参数的集合
				List<Object> obj = new ArrayList<>();
				//通过判断对象中属性是否存在来拼接sql
				if(entity.getName()!=null&&!entity.getName().equals("")){
					sql += "and name like ? ";
					sqlCount +="and name like ? ";
					obj.add("%"+entity.getName()+"%");
				}
				if(entity.getTid()>0){
					sql += "and tid=? ";
					sqlCount += "and tid=? ";
					obj.add(entity.getTid());
				}
				if(entity.getUnit()!=null && !entity.getUnit().equals("")){
					sql +="and unit like ? ";
					sqlCount +="and unit like ? ";
					obj.add("%"+entity.getUnit()+"%");
				}
				if(entity.getSid()>0){
					sql += "and sid=?";
					sqlCount += "and sid=?";
					obj.add(entity.getSid());
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
	public void add(Goods entity) {
		// TODO Auto-generated method stub
		update("update goods set name=?, b_code=?, tid=?,price=?,unit=?,sid=?,outdate=?,num=?,v_price where gid = ",entity.getName(),entity.getB_code(),entity.getTid(),entity.getPrice(),entity.getUnit(),entity.getSid(),entity.getOutdate(),entity.getNum(),entity.getV_price(),entity.getGid());
	}

	@Override
	public void update(Goods entity) {
		// TODO Auto-generated method stub
		update("insert into goods values(null,?,?,?,?,?,?,?,?,?)",entity.getName(),entity.getB_code(),entity.getTid(),entity.getPrice(),entity.getUnit(),entity.getSid(),entity.getOutdate(),entity.getNum(),entity.getV_price());
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		update("delete from goods where gid=?", id);
	}

	@Override
	public Goods getEntity(ResultSet rs) throws Exception {
		Goods g = new Goods();
		g.setGid(rs.getInt(1));
		g.setName(rs.getString(2));
		g.setB_code(rs.getString(3));
		g.setGoodsType(gtd.findById(rs.getInt(4)));
		g.setPrice(rs.getDouble(5));
		g.setUnit(rs.getString(6));
		g.setSup(sd.findById(rs.getInt(7)));
		g.setOutdate(rs.getDate(8));
		g.setNum(rs.getInt(9));
		g.setV_price(rs.getDouble(10));
		return g;
	}

}
