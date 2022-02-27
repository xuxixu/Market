package com.gec.bean;

public class Store {
    private int id;
    private String number;
    private String name;
    private String loc;
    private String remark;

    public Store() {
    }

    public Store(int id, String number, String name, String loc, String remark) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.loc = loc;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "store{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
