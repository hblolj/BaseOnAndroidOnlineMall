package com.app.baseonandroidonlinemall.ui.fragment.user.login;

import com.app.baseonandroidonlinemall.api.HttpResult;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.User;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hblolj on 2017/5/14.
 */

public class LoginPresenter implements IBasePresenter {

    private ILoginDataView mView;

    public LoginPresenter(ILoginDataView view) {
        mView = view;
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }

    public void login(final String username, String password){
        RetrofitService.userApi.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<HttpResult<User>>() {
                    @Override
                    public boolean test(HttpResult<User> userHttpResult) throws Exception {
                        if (userHttpResult.getStatus().equals("0")){
                            mView.showError(userHttpResult.getResult());
                            return false;
                        }
                        return true;
                    }
                })
                .map(new Function<HttpResult<User>, User>() {
                    @Override
                    public User apply(HttpResult<User> userHttpResult) throws Exception {
                        return userHttpResult.getData();
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(User value) {
                        mView.loadUser(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString() + " ");
                        mView.showNetError();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
