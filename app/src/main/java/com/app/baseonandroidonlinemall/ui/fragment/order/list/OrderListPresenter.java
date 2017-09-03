package com.app.baseonandroidonlinemall.ui.fragment.order.list;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.Order;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.IntFunction;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hblolj on 2017/5/18.
 */

public class OrderListPresenter implements IBasePresenter {

    private IOrderListDataView mView;
    private String userId;

    public OrderListPresenter(IOrderListDataView view, String userId) {
        mView = view;
        this.userId = userId;
    }

    @Override
    public void getData(final boolean isRefresh) {
        if (userId.isEmpty()){
            return;
        }
        RetrofitService.orderApi.findOrderById(Integer.parseInt(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<HttpResult<List<Order>>>() {
                    @Override
                    public boolean test(HttpResult<List<Order>> listHttpResult) throws Exception {
                        if (listHttpResult.getStatus().equals("0")) {
                            mView.showError(listHttpResult.getResult());
                            return false;
                        }
                        return true;
                    }
                })
                .map(new Function<HttpResult<List<Order>>, List<Order>>() {
                    @Override
                    public List<Order> apply(HttpResult<List<Order>> listHttpResult) throws Exception {
                        return listHttpResult.getData();
                    }
                }).subscribe(new Observer<List<Order>>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (!isRefresh){
                    mView.showLoading();
                }
            }

            @Override
            public void onNext(List<Order> value) {
                mView.loadData(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Logger.e(e.toString() + " " + isRefresh);
                if (isRefresh) {
                    mView.finishRefresh();
                    // 可以提示对应的信息，但不更新界面
//                            ToastUtils.showToast("刷新失败提示什么根据实际情况");
                } else {
                    mView.showNetError();
                }
            }

            @Override
            public void onComplete() {
                if (isRefresh){
                    mView.finishRefresh();
                }else {
                    mView.hideLoading();
                }
            }
        });
    }

    @Override
    public void getMoreData() {

    }
}
