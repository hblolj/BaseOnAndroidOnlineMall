package com.app.baseonandroidonlinemall.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.bean.Order;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by hblolj on 2017/5/18.
 */

public class OrderListRecyclerViewAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public OrderListRecyclerViewAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        helper.setText(R.id.tv_order_id, item.getId()+"")
                .setText(R.id.tv_order_status, item.getStatus())
                .setText(R.id.tv_total, "订单总价: ￥" + item.getTotal());
        //待收货、商品评价、查看详情、支付相关、确认收货、评价
        helper.getView(R.id.ll_ckxq).setVisibility(View.VISIBLE);
        if (item.getStatus().equals("待付款")){
            //隐藏
            helper.getView(R.id.ll_zhifu).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_qxdd).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_qzf).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_qrsh).setVisibility(View.GONE);
            helper.getView(R.id.tv_pjsp).setVisibility(View.GONE);

        }else if (item.getStatus().equals("待发货")){
            helper.getView(R.id.ll_zhifu).setVisibility(View.GONE);
            helper.getView(R.id.tv_qxdd).setVisibility(View.GONE);
            helper.getView(R.id.tv_qzf).setVisibility(View.GONE);
            helper.getView(R.id.tv_qrsh).setVisibility(View.GONE);
            helper.getView(R.id.tv_pjsp).setVisibility(View.GONE);

        }else if (item.getStatus().equals("待收货")){
            helper.getView(R.id.ll_zhifu).setVisibility(View.GONE);
            helper.getView(R.id.tv_qxdd).setVisibility(View.GONE);
            helper.getView(R.id.tv_qzf).setVisibility(View.GONE);
            helper.getView(R.id.tv_qrsh).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_pjsp).setVisibility(View.GONE);

        }else if (item.getStatus().equals("确认收货")){
            helper.getView(R.id.ll_zhifu).setVisibility(View.GONE);
            helper.getView(R.id.tv_qxdd).setVisibility(View.GONE);
            helper.getView(R.id.tv_qzf).setVisibility(View.GONE);
            helper.getView(R.id.tv_qrsh).setVisibility(View.GONE);
            helper.getView(R.id.tv_pjsp).setVisibility(View.VISIBLE);

        }

    }
}
