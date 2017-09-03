package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.injector.modules.ProductListModule;
import com.app.baseonandroidonlinemall.ui.fragment.product.list.ProductListActivity;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/17.
 */

@PerActivity
@Component(modules = {ProductListModule.class}, dependencies = ApplicationComponents.class)
public interface ProductListComponents {

    void inject(ProductListActivity activity);
}
