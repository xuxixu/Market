package com.gec.bean;

import java.util.Date;

public class Goods {

	private int gid;
	private String name;
	private String b_code;				//条形码
	private int tid;
	private GoodsType goodsType;
	private double price;
	private String unit;				//单位
	private int sid;
	private Supplier sup;
	private Date outdate;
	private int num;
	private double v_price;

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getB_code() {
		return b_code;
	}

	public void setB_code(String b_code) {
		this.b_code = b_code;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public Supplier getSup() {
		return sup;
	}

	public void setSup(Supplier sup) {
		this.sup = sup;
	}

	public Date getOutdate() {
		return outdate;
	}

	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getV_price() {
		return v_price;
	}

	public void setV_price(double v_price) {
		this.v_price = v_price;
	}

	public Goods() {
	}

	@Override
	public String toString() {
		return "Goods{" +
				"gid=" + gid +
				", name='" + name + '\'' +
				", b_code='" + b_code + '\'' +
				", tid=" + tid +
				", goodsType=" + goodsType +
				", price=" + price +
				", unit='" + unit + '\'' +
				", sid=" + sid +
				", sup=" + sup +
				", outdate=" + outdate +
				", num=" + num +
				", v_price=" + v_price +
				'}';
	}
}
