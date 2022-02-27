package com.gec.bean;

public class Supplier {

	private int sid;
	private String number;
	private String name;
	private String remark;

	public Supplier() {
	}

	public Supplier(int sid, String number, String name, String remark) {
		this.sid = sid;
		this.number = number;
		this.name = name;
		this.remark = remark;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Supplier{" +
				"sid=" + sid +
				", number='" + number + '\'' +
				", name='" + name + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
