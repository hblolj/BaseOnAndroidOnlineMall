package com.app.baseonandroidonlinemall.model;

import com.app.baseonandroidonlinemall.api.bean.CategoryData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/5/8.
 * 分类页面 Model
 * 如果访问网络获取数据，应该有一个接口，提供给Presenter层用来回调
 */

public class CategoryModel {

    private List<String> muyin = new ArrayList<>();
    private List<String> baojian = new ArrayList<>();
    private List<String> meirong = new ArrayList<>();
    private List<String> qingshe = new ArrayList<>();
    private List<String> shipin = new ArrayList<>();
    List<CategoryData> mDatas = new ArrayList<>();

    public List<CategoryData> getCategoryData(){
        initDetailItem();
        mDatas.add(new CategoryData("http://116.196.109.166/biyesheji/assets/category/baby.jpg", muyin));
        mDatas.add(new CategoryData("http://116.196.109.166/biyesheji/assets/category/baojian.jpg", baojian));
        mDatas.add(new CategoryData("http://116.196.109.166/biyesheji/assets/category/meirong.jpg", meirong));
        mDatas.add(new CategoryData("http://116.196.109.166/biyesheji/assets/category/qingshes.jpg", qingshe));
        mDatas.add(new CategoryData("http://116.196.109.166/biyesheji/assets/category/shipin.jpg", shipin));
        return mDatas;
    }

    private void initDetailItem() {
        initMuYin();
        initBaojian();
        initMeiRong();
        initQingshe();
        initShiPin();
    }

    private void initShiPin() {
        shipin.add("饼干");
        shipin.add("emart");
        shipin.add("茶饮");
        shipin.add("风味调料");
        shipin.add("橄榄油/植物油");
        shipin.add("谷物代表");
        shipin.add("五谷冲饮");
        shipin.add("巧克力");
        shipin.add("坚果");
        shipin.add("咖啡");
        shipin.add("口香糖");
        shipin.add("炸土豆");
    }

    private void initQingshe() {
        qingshe.add("BURBERRY");
        qingshe.add("CASIO");
        qingshe.add("Chioe");
        qingshe.add("MCM");
        qingshe.add("ICE");
        qingshe.add("围巾");
        qingshe.add("腕表");
        qingshe.add("斜挎包");
        qingshe.add("钱包");
        qingshe.add("配饰");
        qingshe.add("太阳眼镜");
        qingshe.add("手提包");
    }

    private void initMeiRong() {
        meirong.add("彩妆");
        meirong.add("防晒");
        meirong.add("护肤");
        meirong.add("补水");
        meirong.add("香芬");
        meirong.add("面膜");
        meirong.add("洗面奶");
        meirong.add("卸妆水");
        meirong.add("精华");
        meirong.add("精油");
        meirong.add("保湿");
        meirong.add("淡斑");
    }

    private void initBaojian() {
        baojian.add("大王");
        baojian.add("花王");
        baojian.add("1段");
        baojian.add("2段");
        baojian.add("swisse");
        baojian.add("blackmores");
        baojian.add("鱼油");
        baojian.add("胶原蛋白");
        baojian.add("叶酸");
        baojian.add("精华");
        baojian.add("维骨力");
        baojian.add("蜂胶/蜂蜜");
    }

    private void initMuYin() {
        muyin.add("三段");
        muyin.add("益生菌");
        muyin.add("拉拉裤");
        muyin.add("辅食");
        muyin.add("奶粉");
        muyin.add("尿不湿");
        muyin.add("孕妈必备");
        muyin.add("尤妮佳");
        muyin.add("喜宝");
        muyin.add("宝宝用品");
        muyin.add("牛栏");
        muyin.add("爱他美");
    }
}
