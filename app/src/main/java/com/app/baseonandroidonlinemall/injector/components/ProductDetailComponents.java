package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.injector.modules.ProductDetailModule;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.ProductDetailActivity;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/13.
 */

@PerActivity
@Component(modules = ProductDetailModule.class, dependencies = ApplicationComponents.class)
public interface ProductDetailComponents {

    void inject(ProductDetailActivity activity);
}
