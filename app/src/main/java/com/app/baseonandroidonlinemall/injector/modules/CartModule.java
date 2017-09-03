package com.app.baseonandroidonlinemall.injector.modules;

import android.support.annotation.LayoutRes;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.CartItemRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.ui.fragment.cart.CartFragment;
import com.app.baseonandroidonlinemall.ui.fragment.cart.CartPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/15.
 */

@Module
public class CartModule {

    private final CartFragment mCartFragment;
    private final String userId;
    @LayoutRes
    private final int resLayoutId;

    public CartModule(CartFragment cartFragment, String userId, @LayoutRes int resId) {
        mCartFragment = cartFragment;
        this.userId = userId;
        this.resLayoutId = resId;
    }

    @PerFragment
    @Provides
    public CartPresenter provideCartPresenter(){
        return new CartPresenter(mCartFragment, userId);
    }

    @PerFragment
    @Provides
    public CartItemRecyclerViewAdapter provideCartRecyclerViewAdapter(){
        //提供购物项页面的适配器注入
        return new CartItemRecyclerViewAdapter(resLayoutId);
    }
}
