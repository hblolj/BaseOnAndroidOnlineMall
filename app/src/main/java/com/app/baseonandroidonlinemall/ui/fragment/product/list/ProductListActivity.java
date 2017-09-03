package com.app.baseonandroidonlinemall.ui.fragment.product.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.ProductListRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.api.bean.Good;
import com.app.baseonandroidonlinemall.api.bean.GoodGroup;
import com.app.baseonandroidonlinemall.base.BaseActivity;
import com.app.baseonandroidonlinemall.injector.components.DaggerProductListComponents;
import com.app.baseonandroidonlinemall.injector.modules.ProductListModule;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.IProductDetailDataView;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.ProductDetailActivity;
import com.app.baseonandroidonlinemall.ui.fragment.user.login.LoginActivity;
import com.app.baseonandroidonlinemall.utils.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ProductListActivity extends BaseActivity implements IProductListDataView{

    private static final String ID = "id";


    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_lable)
    ImageView iv_lable;
    @BindView(R.id.rl_product_list)
    RecyclerView rl_product_list;
    @Inject
    ProductListRecyclerViewAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_list;
    }

    @Override
    public void initViews() {
        initToolBar(mToolbar, false, "");
        tv_title.setText("商品列表");
        rl_product_list.setLayoutManager(new GridLayoutManager(this, 2));
        rl_product_list.setAdapter(mAdapter);
    }

    public static void launch(Context context, String id){
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(ID, id);
        context.startActivity(intent);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //商品点击事件
                Good item = (Good) adapter.getItem(position);
                ProductDetailActivity.launch(ProductListActivity.this, item.getId());
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //加入购物车
                if (view.getId() == R.id.iv_cart){
                    if(SPUtils.getUser(ProductListActivity.this).getId().isEmpty()){
                        LoginActivity.launch(ProductListActivity.this);
                    }else {
                        //加入购物车
                        Toast.makeText(ProductListActivity.this, "暂未实现!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void initInjector() {
        String id = getIntent().getStringExtra(ID);
        DaggerProductListComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .productListModule(new ProductListModule(id, this, R.layout.item_index_hottestitems))
                .build()
                .inject(this);
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void loadData(List<Good> goods) {
        //拿到商品集合
        mAdapter.setNewData(goods);
    }

    @Override
    public void loadMoreData(List<Good> goods) {

    }

    @Override
    public void loadNoData() {

    }
}
