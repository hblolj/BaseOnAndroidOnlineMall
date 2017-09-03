package com.app.baseonandroidonlinemall.api.bean;

import com.app.baseonandroidonlinemall.api.bean.hot.focusAdItem;
import com.app.baseonandroidonlinemall.api.bean.hot.hotBrandItem;
import com.app.baseonandroidonlinemall.api.bean.hot.spceialAdItem;

import java.util.List;

/**
 * Created by hblolj on 2017/5/10.
 * 除了Hot页的ViewPager其他Fragment的数据对象
 */

public class OtherData {

    private String result;
    private int status;
    private List<focusAdItem> focusAdList;
    private List<spceialAdItem> spceialAdList;
    private List<hotBrandItem> hotBrandList;

    public List<focusAdItem> getFocusAdList() {
        return focusAdList;
    }

    public void setFocusAdList(List<focusAdItem> focusAdList) {
        this.focusAdList = focusAdList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<spceialAdItem> getSpceialAdList() {
        return spceialAdList;
    }

    public void setSpceialAdList(List<spceialAdItem> spceialAdList) {
        this.spceialAdList = spceialAdList;
    }

    public List<hotBrandItem> getHotBrandList() {
        return hotBrandList;
    }

    public void setHotBrandList(List<hotBrandItem> hotBrandList) {
        this.hotBrandList = hotBrandList;
    }
}
