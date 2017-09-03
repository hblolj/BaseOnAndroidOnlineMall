package com.app.baseonandroidonlinemall.api.bean.cart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hblolj on 2017/5/14.
 * 购物项对应的实体
 */

public class CartItem implements Parcelable {

    private String id;
    private String goodsid;
    private String userid;
    private String goodpicture;
    private String goodname;
    private String subtotal;
    private String currprice;
    private String quantity;
    private String orderby;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return id.equals(cartItem.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGoodpicture() {
        return goodpicture;
    }

    public void setGoodpicture(String goodpicture) {
        this.goodpicture = goodpicture;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getCurrprice() {
        return currprice;
    }

    public void setCurrprice(String currprice) {
        this.currprice = currprice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.goodsid);
        dest.writeString(this.userid);
        dest.writeString(this.goodpicture);
        dest.writeString(this.goodname);
        dest.writeString(this.subtotal);
        dest.writeString(this.currprice);
        dest.writeString(this.quantity);
        dest.writeString(this.orderby);
    }

    public CartItem() {
    }

    protected CartItem(Parcel in) {
        this.id = in.readString();
        this.goodsid = in.readString();
        this.userid = in.readString();
        this.goodpicture = in.readString();
        this.goodname = in.readString();
        this.subtotal = in.readString();
        this.currprice = in.readString();
        this.quantity = in.readString();
        this.orderby = in.readString();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel source) {
            return new CartItem(source);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", userid='" + userid + '\'' +
                ", goodpicture='" + goodpicture + '\'' +
                ", goodname='" + goodname + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", currprice='" + currprice + '\'' +
                ", quantity='" + quantity + '\'' +
                ", orderby='" + orderby + '\'' +
                '}';
    }
}
