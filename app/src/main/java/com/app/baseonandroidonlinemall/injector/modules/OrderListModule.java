package com.app.baseonandroidonlinemall.injector.modules;

import android.support.annotation.LayoutRes;

import com.app.baseonandroidonlinemall.adapter.OrderListRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.ui.fragment.order.list.OrderListActivity;
import com.app.baseonandroidonlinemall.ui.fragment.order.list.OrderListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/18.
 */

@Module
public class OrderListModule {

    private final OrderListActivity mOrderListActivity;
    private final String userId;
    @LayoutRes
    private final int resLayoutId;

    public OrderListModule(OrderListActivity orderListActivity, String userId, int resLayoutId) {
        mOrderListActivity = orderListActivity;
        this.userId = userId;
        this.resLayoutId = resLayoutId;
    }

    @PerActivity
    @Provides
    public IBasePresenter provideOrderListPresenter(){
        return new OrderListPresenter(mOrderListActivity, userId);
    }

    @PerActivity
    @Provides
    public OrderListRecyclerViewAdapter provideOrderListRecyclerViewAdapter(){
        return new OrderListRecyclerViewAdapter(resLayoutId);
    }
}
