package com.app.baseonandroidonlinemall.api;

import com.app.baseonandroidonlinemall.api.bean.Good;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hblolj on 2017/5/14.
 * 商品数据的获取接口
 *
 * 获取商品详细信息，商品列表信息
 * 包括  商品组、分类、品牌等一系列和商品相关的查询
 */
public interface IProductApi {

    /**
     * 通过商品 Id 获取商品详细信息
     * @return
     */
    @GET("api/findGoodById")
    Observable<HttpResult<Good>> getGoodById(@Query("goodId") String goodId);

    @GET("api/findGoodListById")
    Observable<HttpResult<List<Good>>> getGoodListById(@Query("id") int id);
}
