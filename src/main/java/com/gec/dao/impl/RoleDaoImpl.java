package com.gec.dao.impl;

import com.gec.bean.PageBean;
import com.gec.bean.Role;
import com.gec.dao.RoleDao;
import com.gec.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends DBUtil<Role> implements RoleDao {

	@Override
	public List<Role> findAll() {
		return query("select id,r_name,remark from role");
	}

	@Override
	public Role findById(int id) {
		return query("select * from role where id=?", id).get(0);
	}

	@Override
	public PageBean<Role> findPage(Role role, int pageIndex) {
		// 需要返回什么类型,就创建个什么类型的空对象
		PageBean<Role> pb = new PageBean<>();
		// 设置当前页
		pb.setPageIndex(pageIndex);
		String sql = "select * from role where 1=1 ";
		String sqlCount = "select count(*) from role where 1=1 ";
		// 创建一个用于存储参数的集合
		List<Object> obj = new ArrayList<>();
		// 通过判断对象中属性是否存在来拼接sql
		if (role.getR_name() != null && !role.getR_name().equals("")) {
			sql += "and r_name like ? ";
			sqlCount += "and r_name like ? ";
			obj.add("%" + role.getR_name() + "%");
		}
		if (role.getRemark() != null && !role.getRemark().equals("")) {
			sql += "and remark like ? ";
			sqlCount += "and remark like ? ";
			obj.add("%" + role.getRemark() + "%");
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
	public void add(Role role) {
		update("insert into role values(null,?,?)", role.getR_name(),role.getRemark());
	}

	@Override
	public void update(Role role) {
		update("update role set r_name=?,remark=? where id=?", role.getR_name(),role.getRemark(),role.getId());
	}

	@Override
	public void delete(int id) {
		update("delete from role where id=?", id);
	}

	@Override
	public Role getEntity(ResultSet rs) throws Exception {
		Role role = new Role();
		role.setId(rs.getInt(1));
		role.setR_name(rs.getString(2));
		role.setRemark(rs.getString(3));
		return role;
	}
}
