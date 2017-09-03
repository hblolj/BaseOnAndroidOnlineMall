package com.app.baseonandroidonlinemall.ui.fragment.cart;

import android.content.Context;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.utils.SPUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hblolj on 2017/5/15.
 */

public class CartPresenter implements IBasePresenter {

    private ICartDataView mView;
    private String userId;

    public CartPresenter(ICartDataView view, String userId) {
        mView = view;
        this.userId = userId;
    }

    @Override
    public void getData(final boolean isRefresh) {
        //通过userId获取到所有的购物项
        if (userId.isEmpty()){
            mView.setViewNoUser();
            return;
        }
        RetrofitService.cartItemApi.getAllCartItem(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<HttpResult<List<CartItem>>>() {
                    @Override
                    public boolean test(HttpResult<List<CartItem>> listHttpResult) throws Exception {
                        if (listHttpResult.getStatus().equals("0")){
                            //业务错误 将Result信息提示给客户端
                            mView.showError(listHttpResult.getResult());
                            return false;
                        }
                        return true;
                    }
                })
                .map(new Function<HttpResult<List<CartItem>>, List<CartItem>>() {
                    @Override
                    public List<CartItem> apply(HttpResult<List<CartItem>> listHttpResult) throws Exception {
                        return listHttpResult.getData();
                    }
                })
                .subscribe(new Observer<List<CartItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!isRefresh){
                            mView.showLoading();
                        }
                    }

                    @Override
                    public void onNext(List<CartItem> value) {
                        mView.loadData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
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

    public void updateCartItemNumber(String cartItemId, String quantity){
        //修改购物项
        RetrofitService.cartItemApi.updateCartItem(cartItemId, quantity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<HttpResult<String>>() {
                    @Override
                    public boolean test(HttpResult<String> voidHttpResult) throws Exception {
                        if (voidHttpResult.getStatus().equals("0")){
                            mView.showError(voidHttpResult.getResult());
                            return false;
                        }
                        return true;
                    }
                });
    }

    public void checkUserStatus(Context context){
        if (SPUtils.getUser(context) == null){
            mView.setViewNoUser();
        }else {
            mView.setViewUser();
        }
    }
}
