package com.app.baseonandroidonlinemall.ui.fragment.order;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by hblolj on 2017/5/17.
 */

public class OrderPresenter implements IBasePresenter {

    private IOrderDataView mView;
    private int userId;

    public OrderPresenter(IOrderDataView view, int userId) {
        mView = view;
        this.userId = userId;
    }

    @Override
    public void getData(boolean isRefresh) {
        //获取用户所有收货地址
    }

    @Override
    public void getMoreData() {

    }

    public void createOrder(List<CartItem> items, int userId, int addressId){
        Gson gson = new Gson();
        String json_items = gson.toJson(items);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json_items.toString());
        RetrofitService.orderApi.createOrder(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<String> value) {
                        if (value.getStatus().equals("0")){
                            mView.showError(value.getResult());
                            mView.loadResult(0);
                        }else {
                            mView.loadResult(1);
                            mView.showError("订单生成成功!");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
