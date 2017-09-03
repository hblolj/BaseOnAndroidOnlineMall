package com.app.baseonandroidonlinemall.model;

import com.app.baseonandroidonlinemall.api.bean.HomeData;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hblolj on 2017/5/9.
 */

public class HomeModel {

    private List<HomeData> mDatas = new ArrayList<>();

    public List<HomeData> getData(){
        HomeData hot = new HomeData("HOT", "0");
        HomeData mz = new HomeData("美 妆", "65");
        HomeData my = new HomeData("母 婴", "42");
        HomeData bj = new HomeData("保 健", "40");
        HomeData sp = new HomeData("食 品", "44");
        HomeData jj = new HomeData("家 居", "281");
        HomeData qs = new HomeData("轻 奢", "269");

        mDatas.add(hot);
        mDatas.add(mz);
        mDatas.add(my);
        mDatas.add(bj);
        mDatas.add(sp);
        mDatas.add(jj);
        mDatas.add(qs);

        return mDatas;
    }

//    private void initTagId(){
//        mTagIds.add("65");
//        mTagIds.add("42");
//        mTagIds.add("40");
//        mTagIds.add("44");
//        mTagIds.add("281");
//        mTagIds.add("269");
//    }
//
//    private void initTitles() {
//        mTitles.add("HOT");
//        mTitles.add("美 妆");
//        mTitles.add("母 婴");
//        mTitles.add("保 健");
//        mTitles.add("食 品");
//        mTitles.add("家 居");
//        mTitles.add("轻 奢");
//    }
}
