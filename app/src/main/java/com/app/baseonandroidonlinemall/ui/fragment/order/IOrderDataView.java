package com.app.baseonandroidonlinemall.ui.fragment.order;

import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.base.ILoadDataView;

/**
 * Created by hblolj on 2017/5/17.
 */

public interface IOrderDataView extends ILoadDataView<CartItem> {

    public void loadResult(int code);
}
