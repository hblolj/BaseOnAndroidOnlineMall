package com.app.baseonandroidonlinemall.api.bean.hot;

import java.util.List;

/**
 * Created by hblolj on 2017/5/10.
 * 轻奢组
 */

public class goodsAdItem {

    private String LabelActiveImg;
    private String LabelId;
    private String LabelImg;
    private String LabelName;
    private String LabelStatus;
    private String LabelType;

    private List<data> data;

    public class data{
        private String LabelActiveImg;
        private String LabelId;
        private String LabelImg;
        private String LabelName;
        private String LabelStatus;
        private String LabelType;

        public String getLabelActiveImg() {
            return LabelActiveImg;
        }

        public void setLabelActiveImg(String labelActiveImg) {
            LabelActiveImg = labelActiveImg;
        }

        public String getLabelId() {
            return LabelId;
        }

        public void setLabelId(String labelId) {
            LabelId = labelId;
        }

        public String getLabelImg() {
            return LabelImg;
        }

        public void setLabelImg(String labelImg) {
            LabelImg = labelImg;
        }

        public String getLabelName() {
            return LabelName;
        }

        public void setLabelName(String labelName) {
            LabelName = labelName;
        }

        public String getLabelStatus() {
            return LabelStatus;
        }

        public void setLabelStatus(String labelStatus) {
            LabelStatus = labelStatus;
        }

        public String getLabelType() {
            return LabelType;
        }

        public void setLabelType(String labelType) {
            LabelType = labelType;
        }
    }

    public String getLabelActiveImg() {
        return LabelActiveImg;
    }

    public void setLabelActiveImg(String labelActiveImg) {
        LabelActiveImg = labelActiveImg;
    }

    public String getLabelId() {
        return LabelId;
    }

    public void setLabelId(String labelId) {
        LabelId = labelId;
    }

    public String getLabelImg() {
        return LabelImg;
    }

    public void setLabelImg(String labelImg) {
        LabelImg = labelImg;
    }

    public String getLabelName() {
        return LabelName;
    }

    public void setLabelName(String labelName) {
        LabelName = labelName;
    }

    public String getLabelStatus() {
        return LabelStatus;
    }

    public void setLabelStatus(String labelStatus) {
        LabelStatus = labelStatus;
    }

    public String getLabelType() {
        return LabelType;
    }

    public void setLabelType(String labelType) {
        LabelType = labelType;
    }

    public List<goodsAdItem.data> getData() {
        return data;
    }

    public void setData(List<goodsAdItem.data> data) {
        this.data = data;
    }
}
