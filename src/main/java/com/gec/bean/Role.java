package com.gec.bean;

public class Role {

	private Integer id;
	private String r_name;
	private String remark;

	public Role() {
	}

	public Role(Integer id, String r_name, String remark) {
		this.id = id;
		this.r_name = r_name;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", r_name='" + r_name + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
