package com.app.baseonandroidonlinemall.api.bean;

import com.app.baseonandroidonlinemall.api.bean.hot.focusAdItem;
import com.app.baseonandroidonlinemall.api.bean.hot.goodsAdItem;
import com.app.baseonandroidonlinemall.api.bean.hot.spceialAdItem;

import java.util.List;

/**
 * Created by hblolj on 2017/5/10.
 * Hot页的数据复杂，封装用以接收
 */

public class HotData {

    private String result;
    private int status;
    private List<focusAdItem> focusAdList;
    private List<goodsAdItem> goodsAdList;
    private List<spceialAdItem> spceialAdList;

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

    public List<focusAdItem> getFocusAdList() {
        return focusAdList;
    }

    public void setFocusAdList(List<focusAdItem> focusAdList) {
        this.focusAdList = focusAdList;
    }

    public List<goodsAdItem> getGoodsAdList() {
        return goodsAdList;
    }

    public void setGoodsAdList(List<goodsAdItem> goodsAdList) {
        this.goodsAdList = goodsAdList;
    }

    public List<spceialAdItem> getSpceialAdList() {
        return spceialAdList;
    }

    public void setSpceialAdList(List<spceialAdItem> spceialAdList) {
        this.spceialAdList = spceialAdList;
    }
}
