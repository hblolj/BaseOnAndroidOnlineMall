package com.app.baseonandroidonlinemall.base;

/**
 * Created by hblolj on 2017/5/7.
 * 基础BaseView接口
 */

public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 显示网络错误，对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    void showNetError();

    /**
     * 完成刷新，新增控件刷新
     */
    void finishRefresh();

    /**
     * 显示业务错误提示
     * @param error
     */
    void showError(String error);

//    <T> LifecycleTransformer<T> bindToLife();
}
