package com.app.baseonandroidonlinemall.ui.fragment.product.list;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.Good;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hblolj on 2017/5/17.
 */

public class ProductListPresenter implements IBasePresenter {

    private IProductListDataView mView;
    private String id;

    public ProductListPresenter(IProductListDataView view, String id) {
        mView = view;
        this.id = id;
    }

    @Override
    public void getData(final boolean isRefresh) {
        //获取商品组
        RetrofitService.productApi.getGoodListById(Integer.parseInt(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<Good>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!isRefresh){
                            mView.showLoading();
                        }
                    }

                    @Override
                    public void onNext(HttpResult<List<Good>> value) {
                        mView.loadData(value.getData());
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
