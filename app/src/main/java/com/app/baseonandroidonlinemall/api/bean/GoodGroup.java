package com.app.baseonandroidonlinemall.api.bean;

import java.util.List;

/**
 * Created by hblolj on 2017/5/9.
 * 商品组实体
 */

public class GoodGroup {

    private String bigPicture;
    private List<String> itemPictures;

    public GoodGroup() {
    }

    public GoodGroup(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public GoodGroup(String bigPicture, List<String> itemPictures) {
        this.bigPicture = bigPicture;
        this.itemPictures = itemPictures;
    }

    public String getBigPicture() {
        return bigPicture;
    }

    public void setBigPicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public List<String> getItemPictures() {
        return itemPictures;
    }

    public void setItemPictures(List<String> itemPictures) {
        this.itemPictures = itemPictures;
    }
}
