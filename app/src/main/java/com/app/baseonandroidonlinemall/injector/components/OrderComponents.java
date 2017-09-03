package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.injector.modules.OrderModule;
import com.app.baseonandroidonlinemall.ui.fragment.order.OrderActivity;
import com.app.baseonandroidonlinemall.ui.fragment.order.list.OrderListActivity;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/17.
 */

@PerActivity
@Component(modules = {OrderModule.class}, dependencies = ApplicationComponents.class)
public interface OrderComponents {
    void inject(OrderActivity activity);
}
