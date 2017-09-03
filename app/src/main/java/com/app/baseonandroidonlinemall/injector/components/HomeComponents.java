package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.injector.modules.HomeModule;
import com.app.baseonandroidonlinemall.ui.fragment.home.HomeFragment;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/9.
 */

@PerFragment
@Component(modules = HomeModule.class, dependencies = ApplicationComponents.class)
public interface HomeComponents {

    void inject(HomeFragment homeFragment);
}
