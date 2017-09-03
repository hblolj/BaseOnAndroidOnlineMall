package com.app.baseonandroidonlinemall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/5/14.
 */

public class CartItemRecyclerViewAdapter extends BaseQuickAdapter<CartItem, BaseViewHolder> {

    //用来存储选中的商品项对应的id
    public List<String> ids = new ArrayList<>();
    //用来存储选中的商品项
    public List<CartItem> cartItems = new ArrayList<>();

    public CartItemRecyclerViewAdapter(@LayoutRes int layoutResId, @Nullable List<CartItem> data) {
        super(layoutResId, data);
    }

    public CartItemRecyclerViewAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CartItem item) {
        Glide.with(mContext)
                .load(RetrofitService.HOME3_HOST + item.getGoodpicture())
                .into((ImageView) helper.getView(R.id.iv_adapter_list_pic));
        //String.format("数量：%d", item.getQuantity())
        //String.format("价格：￥%.2f", item.getSubtotal())
        helper.setText(R.id.item_listview_cart_title, item.getGoodname())
                .setText(R.id.item_listview_cart_count, String.format("数量：%d", Integer.parseInt(item.getQuantity())))
                .setText(R.id.item_listview_cart_price, String.format("价格：￥%.2f", Float.parseFloat(item.getSubtotal())))
                .addOnClickListener(R.id.item_listview_cart_title)
                .addOnClickListener(R.id.item_listview_cart_iconedit)
                .addOnClickListener(R.id.cb_choice)
                .addOnClickListener(R.id.item_listview_cart_count);
        if (ids.contains(item.getGoodsid())){
            //已被选中
            ((CheckBox) helper.getView(R.id.cb_choice)).setChecked(true);
        }else {
            //未被选中
            ((CheckBox) helper.getView(R.id.cb_choice)).setChecked(false);
        }
    }

    /**
     * 全选
     */
    public void selectAll(){
        ids.clear();
        cartItems.clear();
        for (CartItem cartItem : getData()){
            ids.add(cartItem.getGoodsid());
            cartItems.add(cartItem);
        }
        notifyDataSetChanged();
    }

    /**
     * 全部移除
     */
    public void deleteAll(){
        ids.clear();
        cartItems.clear();
        notifyDataSetChanged();
    }
}
