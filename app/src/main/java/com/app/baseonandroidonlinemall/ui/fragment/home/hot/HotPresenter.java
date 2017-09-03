package com.app.baseonandroidonlinemall.ui.fragment.home.hot;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.HotData;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hblolj on 2017/5/9.
 */

public class HotPresenter implements IBasePresenter{

    private IHotDataView mIHotDataView;

    public HotPresenter(IHotDataView IHotDataView) {
        mIHotDataView = IHotDataView;
    }

    @Override
    public void getData(final boolean isRefresh) {
        RetrofitService.homeApi.getHotData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<HttpResult<HotData>>() {
                    @Override
                    public boolean test(HttpResult<HotData> hotDataHttpResult) throws Exception {
                        if (hotDataHttpResult.getStatus().equals("0")){
                            //业务错误 将Result信息提示给客户端
                            mIHotDataView.showError(hotDataHttpResult.getResult());
                            return false;
                        }
                        return true;
                    }
                })
                .map(new Function<HttpResult<HotData>, HotData>() {
                    @Override
                    public HotData apply(HttpResult<HotData> hotDataHttpResult) throws Exception {
                        return hotDataHttpResult.getData();
                    }
                })
                .subscribe(new Observer<HotData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!isRefresh){
                            mIHotDataView.showLoading();
                        }
                    }

                    @Override
                    public void onNext(HotData value) {
                        mIHotDataView.loadData(value);
//                        mIHotDataView.loadAdData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Logger.e(e.toString() + " " + isRefresh);
                    }

                    @Override
                    public void onComplete() {
//                        Logger.w("onCompleted " + isRefresh);
                        if (isRefresh){
                            mIHotDataView.finishRefresh();
                        }else {
                            mIHotDataView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {

    }
}
