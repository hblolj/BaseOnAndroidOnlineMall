package com.app.baseonandroidonlinemall.api.bean.hot;

import java.util.List;

/**
 * Created by hblolj on 2017/5/10.
 */

public class spceialAdItem {

    private String LabelActiveImg;
    private String id;
    private String img;
    private String name;
    private String LabelStatus;
    private String LabelType;
//    private String LabelId;
//    private String LabelImg;
//    private String LabelName;

    private List<Items> items;

    public class Items{
        private String alias;
        private String id;
        private String smallpicture;
        private String mprice;
        private String price;
        private String title;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSmallpicture() {
            return smallpicture;
        }

        public void setSmallpicture(String smallpicture) {
            this.smallpicture = smallpicture;
        }

        public String getMprice() {
            return mprice;
        }

        public void setMprice(String mprice) {
            this.mprice = mprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public String getLabelActiveImg() {
        return LabelActiveImg;
    }

    public void setLabelActiveImg(String labelActiveImg) {
        LabelActiveImg = labelActiveImg;
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

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
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
