package com.gec.bean;

public class OrderUtil {

	private int gid;
	private Goods goods;
	private int salesNum;			//销售数量
	private double salesMoney;		//总销售额
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}
	public double getSalesMoney() {
		return salesMoney;
	}
	public void setSalesMoney(double salesMoney) {
		this.salesMoney = salesMoney;
	}
}
