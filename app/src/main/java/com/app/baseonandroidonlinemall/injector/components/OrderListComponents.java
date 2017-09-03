package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.injector.modules.OrderListModule;
import com.app.baseonandroidonlinemall.ui.fragment.order.list.OrderListActivity;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/18.
 */

@PerActivity
@Component(modules = {OrderListModule.class}, dependencies = ApplicationComponents.class)
public interface OrderListComponents {

    void inject(OrderListActivity activity);
}
