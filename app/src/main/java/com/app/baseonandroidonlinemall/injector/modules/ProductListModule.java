package com.app.baseonandroidonlinemall.injector.modules;

import android.support.annotation.LayoutRes;

import com.app.baseonandroidonlinemall.adapter.ProductListRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.ui.fragment.product.list.ProductListActivity;
import com.app.baseonandroidonlinemall.ui.fragment.product.list.ProductListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/17.
 */

@Module
public class ProductListModule {

    private final String id;
    private final ProductListActivity mProductListActivity;
    @LayoutRes
    private final int layoutResId;

    public ProductListModule(String id, ProductListActivity productListActivity, int layoutResId) {
        this.id = id;
        mProductListActivity = productListActivity;
        this.layoutResId = layoutResId;
    }

    @PerActivity
    @Provides
    public IBasePresenter provideProductListPresenter(){
        return new ProductListPresenter(mProductListActivity, id);
    }

    @PerActivity
    @Provides
    public ProductListRecyclerViewAdapter provideProductListRecyclerViewAdapter(){
        return new ProductListRecyclerViewAdapter(layoutResId);
    }
}
