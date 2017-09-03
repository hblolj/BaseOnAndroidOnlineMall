package com.app.baseonandroidonlinemall.base;

/**
 * Created by hblolj on 2017/5/8.
 * 加载数据的界面接口
 */

public interface ILoadDataView<T> extends IBaseView {

    /**
     * 加载数据
     * @param data
     */
    void loadData(T data);

    /**
     * 加载更多
     * @param data
     */
    void loadMoreData(T data);

    /**
     * 没有数据
     */
    void loadNoData();
}
