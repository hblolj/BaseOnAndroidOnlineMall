package com.app.baseonandroidonlinemall.ui.fragment.product.detail;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.Good;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hblolj on 2017/5/13.
 */

public class ProductDetailPresenter implements IBasePresenter {

    private IProductDetailDataView mViews;
    private String goodId;

    public ProductDetailPresenter(IProductDetailDataView views, String goodId) {
        mViews = views;
        this.goodId = goodId;
    }

    @Override
    public void getData(final boolean isRefresh) {
        RetrofitService.productApi.getGoodById(goodId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<HttpResult<Good>>() {
                    @Override
                    public boolean test(HttpResult<Good> goodHttpResult) throws Exception {
                        if (goodHttpResult.getStatus().equals("0")){
                            mViews.showError(goodHttpResult.getResult());
                            return false;
                        }
                        return true;
                    }
                })
                .map(new Function<HttpResult<Good>, Good>() {
                    @Override
                    public Good apply(HttpResult<Good> goodHttpResult) throws Exception {
                        return goodHttpResult.getData();
                    }
                })
                .subscribe(new Observer<Good>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!isRefresh){
                            mViews.showLoading();
                        }
                    }

                    @Override
                    public void onNext(Good value) {
                        mViews.loadData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString() + " " + isRefresh);
                        if (isRefresh) {
                            mViews.finishRefresh();
                            // 可以提示对应的信息，但不更新界面
//                            ToastUtils.showToast("刷新失败提示什么根据实际情况");
                        } else {
                            mViews.showNetError();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isRefresh){
                            mViews.finishRefresh();
                        }else {
                            mViews.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    /**
     * 生成购物项
     * @param goodId
     * @param userId
     * @param quantity
     */
    public void addCartItem(String goodId, String userId, int quantity){
        RetrofitService.cartItemApi.addCartItem(goodId, userId, quantity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult<String>>() {
                    @Override
                    public void accept(HttpResult<String> voidHttpResult) throws Exception {
                        String status = voidHttpResult.getStatus();
                        if (status.equals("0")){
                            //添加购物项失败
                            mViews.showError(voidHttpResult.getResult());
                        }else {
                            //添加购物项成功
                            mViews.showError("添加成功!");
                        }
                    }
                });
    }
}
