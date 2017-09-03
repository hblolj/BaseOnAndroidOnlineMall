package com.app.baseonandroidonlinemall.api.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/5/7.
 * 分类页面的实体对象
 */

public class CategoryData {
    //分类大图
    private String big_picture;
    //分类详细子项
    private List<String> items = new ArrayList<>();

    public CategoryData(String big_picture, List<String> items) {
        this.big_picture = big_picture;
        this.items = items;
    }

    public CategoryData(String big_picture) {
        this.big_picture = big_picture;
    }

    public CategoryData() {
    }

    public String getBig_picture() {
        return big_picture;
    }

    public void setBig_picture(String big_picture) {
        this.big_picture = big_picture;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
