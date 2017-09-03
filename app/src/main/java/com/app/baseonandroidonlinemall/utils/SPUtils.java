package com.app.baseonandroidonlinemall.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.baseonandroidonlinemall.api.bean.User;

/**
 * Created by hblolj on 2017/1/8.
 */

public class SPUtils {

    private static final String FILE_USER = "user";

    /**
     * 存储基本类型
     */
    public static void saveData(Context context, String key, Object object, String fileName){
        String type = object.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if ("Integer".equals(type)){
            editor.putInt(key, (Integer) object);
        }else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean) object);
        }else if("String".equals(type)){
            editor.putString(key, (String) object);
        }else if("Float".equals(type)){
            editor.putFloat(key, (Float) object);
        }else if("Long".equals(type)){
            editor.putLong(key, (Long) object);
        }
        editor.commit();
    }

    /**
     * 读取基本类型
     */
    public static Object getValue(Context context, String key, Object defValue, String fileName){
        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context
                .MODE_PRIVATE);  //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    public static void saveUser(User user, Context context){
        //反射保存？
        SPUtils.saveData(context, "loginName", user.getLoginname(), FILE_USER);
        SPUtils.saveData(context, "id", user.getId(), FILE_USER);
        SPUtils.saveData(context, "password", user.getPassword(), FILE_USER);
        SPUtils.saveData(context, "registerdata", user.getRegisterdata(), FILE_USER);
        SPUtils.saveData(context, "sex", user.getSex(), FILE_USER);
        SPUtils.saveData(context, "username", user.getUsername(), FILE_USER);
        SPUtils.saveData(context, "level", user.getLevel(), FILE_USER);
        SPUtils.saveData(context, "lastshop", user.getLastshop(), FILE_USER);
    }

    public static User getUser(Context context){
        User user = new User();
        user.setLoginname((String) SPUtils.getValue(context, "loginName", "", FILE_USER));
        user.setId((String) SPUtils.getValue(context, "id", "", FILE_USER));
        user.setPassword((String) SPUtils.getValue(context, "password", "", FILE_USER));
        user.setRegisterdata((String) SPUtils.getValue(context, "registerdata", "", FILE_USER));
        user.setSex((String) SPUtils.getValue(context, "sex", "", FILE_USER));
        user.setUsername((String) SPUtils.getValue(context, "username", "", FILE_USER));
        user.setLevel((String) SPUtils.getValue(context, "level", "", FILE_USER));
        user.setLastshop((String) SPUtils.getValue(context, "lastshop", "", FILE_USER));
        if (user.getId().isEmpty()){
            return null;
        }else {
            return user;
        }
    }

    //将本地登录状态的User置为Null
    public static void setUserNull(Context context){
        SPUtils.saveData(context, "loginName", "", FILE_USER);
        SPUtils.saveData(context, "id", "", FILE_USER);
        SPUtils.saveData(context, "password", "", FILE_USER);
        SPUtils.saveData(context, "registerdata", "", FILE_USER);
        SPUtils.saveData(context, "sex", "", FILE_USER);
        SPUtils.saveData(context, "username", "", FILE_USER);
        SPUtils.saveData(context, "level", "", FILE_USER);
        SPUtils.saveData(context, "lastshop", "", FILE_USER);
    }
}
