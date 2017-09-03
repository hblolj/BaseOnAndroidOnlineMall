package com.app.baseonandroidonlinemall.ui.fragment.order.orderPay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.base.BaseActivity;

import butterknife.BindView;

public class OrderPayActivity extends BaseActivity {

    @BindView(R.id.tv_order_payid)
    TextView tv_order_payid;

    public static void launch(Context context){
        Intent intent = new Intent(context, OrderPayActivity.class);
//        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    public void initViews() {
//        String orderId = getIntent().getStringExtra("orderId");
        tv_order_payid.setText("订单生成成功");

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    public void updateViews(boolean isRefresh) {

    }


    @Override
    public void showError(String error) {

    }
}
