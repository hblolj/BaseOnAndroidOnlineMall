package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.injector.modules.HotModule;
import com.app.baseonandroidonlinemall.injector.modules.OtherModule;
import com.app.baseonandroidonlinemall.ui.fragment.home.other.OtherFragment;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/10.
 */

@PerFragment
@Component(modules = {OtherModule.class}, dependencies = ApplicationComponents.class)
public interface OtherComponents {

    void inject(OtherFragment otherFragment);
}
