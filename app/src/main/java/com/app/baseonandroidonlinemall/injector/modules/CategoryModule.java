package com.app.baseonandroidonlinemall.injector.modules;

import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.ui.fragment.category.CategoryPresenter;
import com.app.baseonandroidonlinemall.ui.fragment.category.CategoryFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/8.
 */

@Module
public class CategoryModule {

    private final CategoryFragment mFragment;

    public CategoryModule(CategoryFragment fragment) {
        mFragment = fragment;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter(){
        return new CategoryPresenter(mFragment);
    }
}
