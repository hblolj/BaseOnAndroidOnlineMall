package com.app.baseonandroidonlinemall.api;

import com.app.baseonandroidonlinemall.api.bean.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hblolj on 2017/5/14.
 * 用户相关数据获取接口
 * 比如登录、注册、信息修改、密码找回、修改
 * 订单查询、购物项获取等
 */

public interface IUserApi {

    /**
     * 用户post表单形式登录
     * @return
     */
    @FormUrlEncoded
    @POST("api/login")
    Observable<HttpResult<User>> login(@Field("loginname") String loginname, @Field("password") String password);
}
