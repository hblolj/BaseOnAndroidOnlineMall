package com.app.baseonandroidonlinemall.injector.modules;

import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.ProductDetailActivity;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.ProductDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/13.
 */

@Module
public class ProductDetailModule {

    private final ProductDetailActivity mProductDetailActivity;
    private final String goodId;

    public ProductDetailModule(ProductDetailActivity productDetailActivity, String goodId) {
        mProductDetailActivity = productDetailActivity;
        this.goodId = goodId;
    }

    @PerActivity
    @Provides
    public ProductDetailPresenter provideProductDetailPresenter(){
        return new ProductDetailPresenter(mProductDetailActivity, goodId);
    }
}
