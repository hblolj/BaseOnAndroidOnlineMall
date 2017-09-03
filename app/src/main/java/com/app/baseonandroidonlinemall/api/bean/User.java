package com.app.baseonandroidonlinemall.api.bean;

/**
 * Created by hblolj on 2017/5/14.
 * 会员实体
 */

public class User {

    private String id;
    private String username;
    private String password;
    private String loginname;
    private String level;
    private String registerdata;
    private String lastshop;
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRegisterdata() {
        return registerdata;
    }

    public void setRegisterdata(String registerdata) {
        this.registerdata = registerdata;
    }

    public String getLastshop() {
        return lastshop;
    }

    public void setLastshop(String lastshop) {
        this.lastshop = lastshop;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
