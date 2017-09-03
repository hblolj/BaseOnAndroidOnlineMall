package com.app.baseonandroidonlinemall.injector.modules;


import com.app.baseonandroidonlinemall.adapter.CheckoutAdapter;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.ui.fragment.order.OrderActivity;
import com.app.baseonandroidonlinemall.ui.fragment.order.OrderPresenter;
import com.app.baseonandroidonlinemall.ui.fragment.order.list.OrderListPresenter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/17.
 */

@Module
public class OrderModule {

    private final int userId;
    private final OrderActivity mOrderActivity;

    public OrderModule(OrderActivity orderActivity, int userId) {
        this.userId = userId;
        mOrderActivity = orderActivity;
    }

    @PerActivity
    @Provides
    public OrderPresenter provideOrderPresenter(){
        return new OrderPresenter(mOrderActivity, userId);
    }

    @PerActivity
    @Provides
    public CheckoutAdapter provideOrderItemGridViewAdapter(){
        return new CheckoutAdapter(mOrderActivity);
    }
}
