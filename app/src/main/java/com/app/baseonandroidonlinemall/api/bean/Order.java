package com.app.baseonandroidonlinemall.api.bean;

/**
 * Created by hblolj on 2017/5/18.
 * 订单实体
 */

public class Order {

    private int id;
    private int addressid;
    private String ordertime;
    private String status;
    private String total;
    private int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
