package com.app.baseonandroidonlinemall.api;

import com.app.baseonandroidonlinemall.api.bean.Order;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hblolj on 2017/5/17.
 * 订单数据获取接口
 */

public interface IOrderApi {

    /**
     * 生成订单
     * @return
     */
    @POST("api/createOrder")
    Observable<HttpResult<String>> createOrder(@Body RequestBody ids);

    /**
     * 生成订单
     * @return
     */
    @POST("api/createOrder")
    Observable<HttpResult<String>> createOrder(@Body CartItem cartItem);

    /**
     * 根据用户 Id 获取订单列表
     */
    @GET("api/findOrderById")
    Observable<HttpResult<List<Order>>> findOrderById(@Query("userId") int userId);
}
