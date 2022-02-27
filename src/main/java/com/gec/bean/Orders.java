package com.gec.bean;

import java.util.Date;

public class Orders {
    private int oid;
    private int uid;
    private User user;
    private int gid;
    private Goods goods;
    private int sid;
    private Store store;
    private Date buydate;
    private int bnum;
    private double subtotal;

    public Orders() {
    }

    public Orders(int oid, int uid, User user, int gid, Goods goods, int sid, Store store, Date buydate, int bnum, double subtotal) {
        this.oid = oid;
        this.uid = uid;
        this.user = user;
        this.gid = gid;
        this.goods = goods;
        this.sid = sid;
        this.store = store;
        this.buydate = buydate;
        this.bnum = bnum;
        this.subtotal = subtotal;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getBuydate() {
        return buydate;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }

    public int getBnum() {
        return bnum;
    }

    public void setBnum(int bnum) {
        this.bnum = bnum;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", user=" + user +
                ", gid=" + gid +
                ", goods=" + goods +
                ", sid=" + sid +
                ", store=" + store +
                ", buydate=" + buydate +
                ", bnum=" + bnum +
                ", subtotal=" + subtotal +
                '}';
    }
}
