package com.app.baseonandroidonlinemall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.bean.hot.spceialAdItem;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.ProductDetailActivity;
import com.app.baseonandroidonlinemall.ui.fragment.product.list.ProductListActivity;
import com.app.baseonandroidonlinemall.viewHolder.MyViewHolder;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hblolj on 2017/5/9.
 * GoodsADList
 */

public class HotRecyclerViewAdapter extends BaseQuickAdapter<spceialAdItem, MyViewHolder> {

    public HotRecyclerViewAdapter(@LayoutRes int layoutResId, @Nullable List<spceialAdItem> data) {
        super(layoutResId, data);
    }

    public HotRecyclerViewAdapter(@Nullable List<spceialAdItem> data) {
        super(data);
    }

    public HotRecyclerViewAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(MyViewHolder helper, final spceialAdItem item) {
//        Glide.with(mContext)
//                .load(item.getImg())
//                .centerCrop()
//                .into((ImageView) helper.getView(R.id.iv_good_group_big_picture));
        Glide.with(mContext)
                .load(item.getImg())
                .centerCrop()
                .into(helper.iv_big_picture);
        //给商品组的大图设置点击事件
        helper.addOnClickListener(R.id.iv_good_group_big_picture);

        HotHorizontalRecyclerViewAdapter adapter =
                new HotHorizontalRecyclerViewAdapter(R.layout.item_hot_horizontal_recyclerview, item.getItems());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //横划栏的点击事件
                spceialAdItem.Items items = (spceialAdItem.Items) adapter.getItem(position);
                if ("更多好货".equals(items.getAlias())){
                    //跳到商品列表页面
                    ProductListActivity.launch(mContext, item.getId());
                }else {
                    //跳转到商品详情页面
                    ProductDetailActivity.launch(mContext, items.getId());
                 }
                Toast.makeText(mContext, "横划栏 商品Id :　" + items.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        helper.rl_horizontal.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        helper.rl_horizontal.setAdapter(adapter);
//        RecyclerView rl = helper.getView(R.id.rl_good_group_horizontal);
//        rl.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
//        rl.setAdapter(adapter);
    }

    @Override
    public void setNewData(@Nullable List<spceialAdItem> data) {
        super.setNewData(data);
    }

    class ViewHolder extends BaseViewHolder{

        ImageView iv_big_picture;
        RecyclerView rl_horizontal;

        public ViewHolder(View view) {
            super(view);
            iv_big_picture = (ImageView) view.findViewById(R.id.iv_good_group_big_picture);
            rl_horizontal = (RecyclerView) view.findViewById(R.id.rl_good_group_horizontal);
        }
    }
}
