package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.injector.modules.CategoryModule;
import com.app.baseonandroidonlinemall.ui.fragment.category.CategoryFragment;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/8.
 */

@PerFragment
@Component(modules = CategoryModule.class, dependencies = ApplicationComponents.class)
public interface CategoryComponents {

    void inject(CategoryFragment categoryFragment);
}
