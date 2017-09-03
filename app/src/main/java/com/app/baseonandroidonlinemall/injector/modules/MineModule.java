package com.app.baseonandroidonlinemall.injector.modules;

import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.ui.fragment.mine.MineFragment;
import com.app.baseonandroidonlinemall.ui.fragment.mine.MinePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/14.
 */

@Module
public class MineModule {

    private final MineFragment mMineFragment;

    public MineModule(MineFragment mineFragment) {
        mMineFragment = mineFragment;
    }

    @PerFragment
    @Provides
    public IBasePresenter provideMinePresenter(){
        return new MinePresenter();
    }
}
