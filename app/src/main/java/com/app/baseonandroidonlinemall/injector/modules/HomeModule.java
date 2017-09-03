package com.app.baseonandroidonlinemall.injector.modules;

import com.app.baseonandroidonlinemall.adapter.HomeViewPagerAdapter;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.HomeFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/9.
 */

@Module
public class HomeModule {

    private final HomeFragment mHomeFragment;

    public HomeModule(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter(){
        return new HomePresenter(mHomeFragment);
    }

    @PerFragment
    @Provides
    public HomeViewPagerAdapter provideViewPager(){
        return new HomeViewPagerAdapter(mHomeFragment.getChildFragmentManager());
    }
}
