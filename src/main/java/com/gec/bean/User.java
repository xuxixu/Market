package com.gec.bean;

public class User {
	private int uid;
	private String number;
	private String userName;
	private String password;
	private String sex;
	private String phone;
	private int rid;	//外键ID,用于增删改时使用
	private Role role;  //外键对象  用于查询时使用
	private String remark;

	public User() {
	}

	public User(int uid, String number, String userName, String password, String sex, String phone, int rid, Role role, String remark) {
		this.uid = uid;
		this.number = number;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.rid = rid;
		this.role = role;
		this.remark = remark;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", number='" + number + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", sex='" + sex + '\'' +
				", phone='" + phone + '\'' +
				", rid=" + rid +
				", role=" + role +
				", remark='" + remark + '\'' +
				'}';
	}
}
