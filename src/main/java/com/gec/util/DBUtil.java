package com.gec.util;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class DBUtil<T> {

	private Connection conn=null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	public Connection getConn() throws Exception{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		p.load(in);
		Class.forName(p.getProperty("driver"));
		conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("uname"), p.getProperty("password"));
		return conn;
	}
	
	/*
	 * 统一更新方法
	 */
	public boolean update(String sql,Object...obj){
		try {
			pst = getConn().prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pst.setObject(i+1, obj[i]);
			}
			int row = pst.executeUpdate();
			if(row>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * 统一查询方法
	 */
	public List<T> query(String sql,Object...obj){
		List<T> list = new ArrayList<>();
		try {
			pst = getConn().prepareStatement(sql);
			//对sql中的占位符赋值
			for (int i = 0; i < obj.length; i++) {
				pst.setObject(i+1, obj[i]);
			}
			rs = pst.executeQuery();
			while(rs.next()){
				//无法确定获取到对象,需要到子类重写获取具体对象
				T t = getEntity(rs);
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public abstract T getEntity(ResultSet rs) throws Exception;
	
	/*
	 * 统一查询出数据总记录数
	 */
	public int getCount(String sql,Object...obj){
		try {
			pst = getConn().prepareStatement(sql);
			//对sql中的占位符赋值
			for (int i = 0; i < obj.length; i++) {
				pst.setObject(i+1, obj[i]);
			}
			rs = pst.executeQuery();
			if(rs.next()){
				//直接获取到第一个列中的总记录数
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/*
	 * 统一关闭方法
	 */
	public void getClose(ResultSet rs,PreparedStatement pst,Connection conn){
		try {
			if(rs!=null)
				rs.close();
			if(pst!=null)
				pst.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
