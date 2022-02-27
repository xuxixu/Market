package com.gec.bean;

public class GoodsType {

	private int tid;
	private String tname;
	private String remark;

	public GoodsType() {
	}

	public GoodsType(int tid, String tname, String remark) {
		this.tid = tid;
		this.tname = tname;
		this.remark = remark;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "GoodsType{" +
				"tid=" + tid +
				", tname='" + tname + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
