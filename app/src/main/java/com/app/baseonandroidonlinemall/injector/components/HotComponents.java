package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.injector.modules.HotModule;
import com.app.baseonandroidonlinemall.ui.fragment.home.hot.HotFragment;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/9.
 */

@PerFragment
@Component(modules = HotModule.class, dependencies = ApplicationComponents.class)
public interface HotComponents {

    void inject(HotFragment hotFragment);
}
