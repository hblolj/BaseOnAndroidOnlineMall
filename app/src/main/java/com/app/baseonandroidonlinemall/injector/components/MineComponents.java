package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.injector.modules.MineModule;
import com.app.baseonandroidonlinemall.ui.fragment.mine.MineFragment;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/14.
 */

@PerFragment
@Component(modules = MineModule.class, dependencies = ApplicationComponents.class)
public interface MineComponents {

    void inject(MineFragment fragment);
}
