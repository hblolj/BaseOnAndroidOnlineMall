package com.app.baseonandroidonlinemall.api.bean;

/**
 * Created by hblolj on 2017/5/10.
 * Home模块的ViewPager中的Fragment的标题的与Id
 */

public class HomeData {

    private String title;
    private String tagId;

    public HomeData() {
    }

    public HomeData(String title, String tagId) {
        this.title = title;
        this.tagId = tagId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
