package com.app.baseonandroidonlinemall.injector.modules;

import android.support.annotation.LayoutRes;

import com.app.baseonandroidonlinemall.adapter.HotRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.hot.HotFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.hot.HotPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/9.
 */

@Module
public class HotModule {

    @LayoutRes
    private final int resLayoutId;

    private final HotFragment mHotFragment;

    public HotModule(int resLayoutId, HotFragment hotFragment) {
        this.resLayoutId = resLayoutId;
        mHotFragment = hotFragment;
    }

    @PerFragment
    @Provides
    public IBasePresenter provideHotPresenter(){
        return new HotPresenter(mHotFragment);
    }

    @PerFragment
    @Provides
    public HotRecyclerViewAdapter provideHotRecyclerViewAdapter(){
        return new HotRecyclerViewAdapter(resLayoutId);
    }
}
