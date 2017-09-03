package com.app.baseonandroidonlinemall.api.bean.hot;

/**
 * Created by hblolj on 2017/5/10.
 * 热门品牌按钮
 */

public class hotBrandItem {

    private String id;
    private String img;
    private String name;
    private String categoryid;

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
