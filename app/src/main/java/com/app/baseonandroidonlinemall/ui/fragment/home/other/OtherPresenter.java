package com.app.baseonandroidonlinemall.ui.fragment.home.other;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.OtherData;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hblolj on 2017/5/10.
 */

public class OtherPresenter implements IBasePresenter{

    private IOtherDataView mView;
    private String tagId;

    public OtherPresenter(IOtherDataView views, String id) {
        mView = views;
        tagId = id;
    }

    @Override
    public void getData(final boolean isRefresh) {
        RetrofitService.homeApi.getOtherDataById(tagId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<HttpResult<OtherData>>() {
                    @Override
                    public boolean test(HttpResult<OtherData> otherDataHttpResult) throws Exception {
                        if (otherDataHttpResult.getStatus().equals("0")){
                            //业务错误 将Result信息提示给客户端
                            mView.showError(otherDataHttpResult.getResult());
                            return false;
                        }
                        return true;
                    }
                })
                .map(new Function<HttpResult<OtherData>, OtherData>() {
                    @Override
                    public OtherData apply(HttpResult<OtherData> otherDataHttpResult) throws Exception {
                        return otherDataHttpResult.getData();
                    }
                })
                .subscribe(new Observer<OtherData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!isRefresh){
                            mView.showLoading();
                        }
                    }

                    @Override
                    public void onNext(OtherData value) {
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
