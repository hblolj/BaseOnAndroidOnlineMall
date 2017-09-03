package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.injector.modules.CartModule;
import com.app.baseonandroidonlinemall.ui.fragment.cart.CartFragment;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/15.
 */
@PerFragment
@Component(modules = {CartModule.class}, dependencies = ApplicationComponents.class)
public interface CartComponents {
    void inject(CartFragment cartFragment);
}
