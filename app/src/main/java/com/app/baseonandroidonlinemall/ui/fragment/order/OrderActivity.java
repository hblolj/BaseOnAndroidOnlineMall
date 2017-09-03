package com.app.baseonandroidonlinemall.ui.fragment.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.MainActivity;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.CheckoutAdapter;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.base.BaseActivity;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.components.DaggerOrderComponents;
import com.app.baseonandroidonlinemall.injector.modules.OrderModule;
import com.app.baseonandroidonlinemall.ui.fragment.order.orderPay.OrderPayActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity<OrderPresenter> implements IOrderDataView{

    //收货人姓名
    @BindView(R.id.item_address_user)
    TextView item_address_user;
    //收货人联系方式
    @BindView(R.id.item_address_tele)
    TextView item_address_tele;
    //收货地址
    @BindView(R.id.item_address_address)
    TextView item_address_address;
    //选中的购物项列表
    @BindView(R.id.gvCheckout)
    GridView gvCheckout;
    //购物项总价
    @BindView(R.id.fragment_checkout_totalprice)
    TextView fragment_checkout_totalprice;
    //运费
    @BindView(R.id.fragment_checkout_transportation)
    TextView fragment_checkout_transportation;
    //税费
    @BindView(R.id.fragment_checkout_taxation)
    TextView fragment_checkout_taxation;
    //最终总价
    @BindView(R.id.tv_cart_Allprice)
    TextView tv_cart_Allprice;
    //确认提交 生成订单
    @BindView(R.id.tv_qrtj)
    TextView tv_qrtj;
    @Inject
    CheckoutAdapter mAdapter;

    private List<CartItem> mCartItems = new ArrayList<>();
    private List<CartItem> cartItems;
    private int userId;

    private static final String USER_ID = "userId";
    private static final String CARTITEMS = "cartItems";

    private float subTotal = 0;

    public static void launch(Context context, List<CartItem> cartItems, int userId){
        Intent intent = new Intent(context, OrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(USER_ID, userId);
        bundle.putParcelableArrayList(CARTITEMS, (ArrayList<? extends Parcelable>) cartItems);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initViews() {
        //购物需车中选中的购物项
        cartItems = getIntent().getExtras().getParcelableArrayList(CARTITEMS);
        //用来生成订单的购物项
        mCartItems.clear();
        mCartItems.addAll(cartItems);
        mAdapter.addNewData(mCartItems);
        gvCheckout.setAdapter(mAdapter);
        //初始化商品总价、含运费的总价
        for (CartItem c : cartItems){
            float total = Float.parseFloat(c.getSubtotal());
            subTotal += total;
        }
        fragment_checkout_totalprice.setText("￥" + subTotal);
        tv_cart_Allprice.setText("总计(含运费): ￥" + subTotal);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void initInjector() {
        userId = getIntent().getIntExtra(USER_ID, -1);
        DaggerOrderComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .orderModule(new OrderModule(this, userId))
                .build()
                .inject(this);
    }

    @Override
    public void updateViews(boolean isRefresh) {
        //请求获取用户所有的收货地址
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadData(CartItem data) {
        //拿到收货地址
    }

    @Override
    public void loadMoreData(CartItem data) {

    }

    @Override
    public void loadNoData() {

    }

    @OnClick(R.id.tv_qrtj)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_qrtj :
                //提交订单
                mPresenter.createOrder(mCartItems, userId, 1);
                break;
        }
    }

    @Override
    public void loadResult(int code) {
        if (code == 1){
            //生成订单成功
            cartItems.clear();
            //跳转到新页面
            this.finish();
            OrderPayActivity.launch(this);
        }else {
            //生成订单失败
        }
    }
}
