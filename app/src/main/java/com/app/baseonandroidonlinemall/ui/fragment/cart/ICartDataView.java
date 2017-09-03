package com.app.baseonandroidonlinemall.ui.fragment.cart;

import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.base.ILoadDataView;

import java.util.List;

/**
 * Created by hblolj on 2017/5/15.
 */

public interface ICartDataView extends ILoadDataView<List<CartItem>> {

    //设置购物车页面为未登录状态
    public void setViewNoUser();

    //设置购物车页面登录状态
    public void setViewUser();
}
