package com.gec.dao.impl;

import com.gec.bean.PageBean;
import com.gec.bean.User;
import com.gec.dao.RoleDao;
import com.gec.dao.UserDao;
import com.gec.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DBUtil<User> implements UserDao {

	RoleDao rd = new RoleDaoImpl();

	@Override
	public PageBean<User> findPage(User user,int pageIndex) {
		//需要返回什么类型,就创建个什么类型的空对象
		PageBean<User> pb = new PageBean<>();
		//设置当前页
		pb.setPageIndex(pageIndex);
		String sql = "select * from user where 1=1 ";
		String sqlCount = "select count(*) from user where 1=1 ";
		//创建一个用于存储参数的集合
		List<Object> obj = new ArrayList<>();
		//通过判断对象中属性是否存在来拼接sql
		if(user.getNumber()!=null&&!user.getNumber().equals("")){
			sql += "and number like ? ";
			sqlCount +="and number like ? ";
			obj.add("%"+user.getNumber()+"%");
		}
		if(user.getUserName()!=null && !user.getUserName().equals("")){
			sql += "and username like ? ";
			sqlCount += "and username like ? ";
			obj.add("%"+user.getUserName()+"%");
		}
		if(user.getSex()!=null && !user.getSex().equals("")){
			sql +="and sex = ? ";
			sqlCount +="and sex = ? ";
			obj.add(user.getSex());
		}
		if(user.getPhone()!=null && !user.getPhone().equals("")){
			sql += "and phone like ?";
			sqlCount += "and phone like ?";
			obj.add("%"+user.getPhone()+"%");
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
	public User findById(int id) {
		List<User> list = query("select * from user where uid=?", id);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	@Override
	public User checkNumber(String number) {
		List<User> list = query("select * from user where number=?", number);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	@Override
	public User login(String number, String password) {
		List<User> list = query("select * from user where number=? and password=?", number,password);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void add(User emp) {
		update("insert into user values(null,?,?,?,?,?,?,?)",emp.getNumber(),emp.getUserName(),emp.getPassword(),emp.getSex(),emp.getPhone(),emp.getRid(),emp.getRemark());
	}

	@Override
	public void update(User emp) {
		update("update user set username=?,password=?,sex=?,phone=?,role=?,remark=? where uid=?", emp.getUserName(),emp.getPassword(),emp.getSex(),emp.getPhone(),emp.getRid(),emp.getRemark(),emp.getUid());
	}

	@Override
	public void delete(int id) {
		update("delete from user where uid=?", id);
	}

	@Override
	public User getEntity(ResultSet rs) throws Exception {
		User emp = new User();
		emp.setUid(rs.getInt(1));
		emp.setNumber(rs.getString(2));
		emp.setUserName(rs.getString(3));
		emp.setPassword(rs.getString(4));
		emp.setSex(rs.getString(5));
		emp.setPhone(rs.getString(6));
		//将外键从rs中获取,并调用Role的主键查询出对象
		emp.setRole(rd.findById(rs.getInt(7)));
		emp.setRemark(rs.getString(8));
		return emp;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
