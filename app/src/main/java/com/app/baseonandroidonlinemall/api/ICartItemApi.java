package com.app.baseonandroidonlinemall.api;

import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hblolj on 2017/5/14.
 * 购物项对应的数据获取接口
 */

public interface ICartItemApi {

    /**
     * 添加购物项成功，返回该用户的所有购物项
     * @param goodId
     * @param userId
     * @param quantity
     * @return
     */
    @FormUrlEncoded
    @POST("api/addCartItem")
    Observable<HttpResult<String>> addCartItem(@Field("goodId") String goodId,
                                                  @Field("userId") String userId,
                                @Field("quantity") int quantity);

    /**
     * 拿到用户所有购物项
     */
    @FormUrlEncoded
    @POST("api/getAllCartItem")
    Observable<HttpResult<List<CartItem>>> getAllCartItem(@Field("userId") String userId);

    /**
     * 修改购物项数量
     */
    @FormUrlEncoded
    @POST("api/updateCartItem")
    Observable<HttpResult<String>> updateCartItem(@Field("cartItemId") String cartItemId,
                                                @Field("quantity") String quantity);
}
