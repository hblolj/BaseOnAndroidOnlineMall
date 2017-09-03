package com.app.baseonandroidonlinemall.ui.fragment.order.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.OrderListRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.api.bean.Order;
import com.app.baseonandroidonlinemall.base.BaseActivity;
import com.app.baseonandroidonlinemall.injector.components.DaggerOrderListComponents;
import com.app.baseonandroidonlinemall.injector.modules.OrderListModule;
import com.app.baseonandroidonlinemall.utils.SPUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class OrderListActivity extends BaseActivity implements IOrderListDataView {

    @BindView(R.id.rl_order_list)
    RecyclerView rl_order_list;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Inject
    OrderListRecyclerViewAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_list;
    }

    public static void launch(Context context, String userId){
        Intent intent = new Intent(context, OrderListActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    @Override
    public void initViews() {
        initToolBar(mToolbar, true, "");
        tv_title.setText("我的订单");
        rl_order_list.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false));
        rl_order_list.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void initInjector() {
        String userId = getIntent().getStringExtra("userId");
        DaggerOrderListComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .orderListModule(new OrderListModule(this, userId, R.layout.item_order_list_recyclerview))
                .build()
                .inject(this);
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(List<Order> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreData(List<Order> data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
