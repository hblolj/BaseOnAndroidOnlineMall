package com.app.baseonandroidonlinemall.api.bean;

/**
 * Created by hblolj on 2017/5/9.
 * 商品实体
 */

public class Good {

    private String id;
    private String alias;
    private String title;
    private String bigpicture;
    private String smallpicture;
    private String price;
    private String mprice;
    private String place;
    private String spec;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBigpicture() {
        return bigpicture;
    }

    public void setBigpicture(String bigpicture) {
        this.bigpicture = bigpicture;
    }

    public String getSmallpicture() {
        return smallpicture;
    }

    public void setSmallpicture(String smallpicture) {
        this.smallpicture = smallpicture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMprice() {
        return mprice;
    }

    public void setMprice(String mprice) {
        this.mprice = mprice;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
