package com.app.baseonandroidonlinemall.api;

import com.app.baseonandroidonlinemall.api.bean.HotData;
import com.app.baseonandroidonlinemall.api.bean.OtherData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.app.baseonandroidonlinemall.api.RetrofitService.AVOID_HTTP403_FORBIDDEN;
import static com.app.baseonandroidonlinemall.api.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by hblolj on 2017/5/9.
 * Home页面的数据获取接口
 *
 * 获取Hot页和Other页的数据
 */

public interface IHomeApi {

//    @Headers({CACHE_CONTROL_NETWORK, AVOID_HTTP403_FORBIDDEN})
//    @GET("index/getIndexData")
//    Observable<HotData> getHotData();
//
//    @GET("index/getIndexSubData")
//    Observable<OtherData> getOtherDataById(@Query("tagId") String tagId);

    //从自己的服务器获取数据
    @Headers({CACHE_CONTROL_NETWORK, AVOID_HTTP403_FORBIDDEN})
    @GET("api/getIndexData")
    Observable<HttpResult<HotData>> getHotData();

    @GET("api/getIndexSubData")
    Observable<HttpResult<OtherData>> getOtherDataById(@Query("tagId") String tagId);

//    @GET("index/getIndexData")
//    Call<ResponseBody> getHotData();
}
