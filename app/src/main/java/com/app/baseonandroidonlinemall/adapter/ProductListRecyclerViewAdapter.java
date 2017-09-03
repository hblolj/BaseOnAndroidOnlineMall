package com.app.baseonandroidonlinemall.adapter;

import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.Good;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by hblolj on 2017/5/17.
 */

public class ProductListRecyclerViewAdapter extends BaseQuickAdapter<Good, BaseViewHolder> {

    public ProductListRecyclerViewAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Good item) {
        Glide.with(mContext)
                .load(RetrofitService.HOME3_HOST + item.getBigpicture())
                .fitCenter()
                .into((ImageView) helper.getView(R.id.product_list_item_image));
        helper.setText(R.id.product_list_item_title, item.getTitle())
                .setText(R.id.product_list_item_mprice, "市场价：￥" + item.getMprice())
                .setText(R.id.product_list_item_price, "￥" + item.getPrice())
                .addOnClickListener(R.id.iv_cart);
    }
}
