package com.app.baseonandroidonlinemall.ui.fragment.product.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.Good;
import com.app.baseonandroidonlinemall.api.bean.User;
import com.app.baseonandroidonlinemall.base.BaseActivity;
import com.app.baseonandroidonlinemall.injector.components.DaggerProductDetailComponents;
import com.app.baseonandroidonlinemall.injector.modules.ProductDetailModule;
import com.app.baseonandroidonlinemall.ui.fragment.user.login.LoginActivity;
import com.app.baseonandroidonlinemall.utils.SPUtils;
import com.app.baseonandroidonlinemall.widget.NumberPicker;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements IProductDetailDataView{

    private static final String GOODID = "goodId";

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView toolbar_title;
    @BindView(R.id.iv_good)
    ImageView good_big_picture;
    @BindView(R.id.tv_product_details_title)
    TextView product_details_title;
    @BindView(R.id.tv_product_details_price)
    TextView product_details_price;
    @BindView(R.id.tv_product_details_market_price)
    TextView product_details_market_price;
//    @BindView(R.id.selectedList)
//    FlowLayout selectedList;
    @BindView(R.id.tvAlias)
    TextView product_alias;
    @BindView(R.id.tvPlace)
    TextView product_place;
    @BindView(R.id.np_product_details_quantity)
    NumberPicker product_details_quantity;
    @BindView(R.id.btnPay)
    Button btnPay;
    @BindView(R.id.add_to_cart)
    Button add_to_cart;
    @BindView(R.id.rl_cart_icon)
    RelativeLayout rl_cart_icon;
    //购物车中购物项的数量
    @BindView(R.id.badge)
    TextView badge;
    //储存当前页面的商品信息
    private Good mGood;

    public static void launch(Context context, String goodId){
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(GOODID, goodId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initViews() {
        toolbar_title.setText("宝贝详情");
        initToolBar(mToolbar, true, "");
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void initInjector() {
        String goodId = getIntent().getStringExtra(GOODID);
        DaggerProductDetailComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .productDetailModule(new ProductDetailModule(this, goodId))
                .build()
                .inject(this);
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.getData(false);
    }

    @Override
    public void loadData(Good data) {
        //是否应该获取购物车消息，显示购物车中购物项的数量?
        mGood = data;
        //获取到商品信息
        Logger.d("商品详情大图: " + RetrofitService.HOME3_HOST + data.getBigpicture());
        Glide.with(this)
                .load(RetrofitService.HOME3_HOST + data.getBigpicture())
                .fitCenter()
                .into(good_big_picture);

        product_details_title.setText(data.getTitle());
        product_details_price.setText("￥" + data.getPrice());
        product_details_market_price.setText("￥" + data.getMprice());
        product_alias.setText(data.getAlias());
        product_place.setText(data.getPlace());

        //规格需要判断 显示  暂时不管规格问题
    }

    @Override
    public void loadMoreData(Good data) {

    }

    @Override
    public void loadNoData() {

    }

    /**
     * 立即购买、加入购物车、购物车图标的点击事件
     * @param view
     */
    @OnClick({R.id.btnPay, R.id.add_to_cart, R.id.rl_cart_icon})
    public void onClick(View view){
        //下面三个功能都需要判断用户是否已经登录
        User user = SPUtils.getUser(this);
        if (user == null){
            //用户未登录
            Toast.makeText(this, "请先登录!", Toast.LENGTH_SHORT).show();
            LoginActivity.launch(this);
            return;
        }
        switch (view.getId()){
            case R.id.btnPay:
                //立即购买
                break;
            case R.id.add_to_cart:
                //加入购物车
                //数量、商品Id、用户Id、规格 生成购物项
                mPresenter.addCartItem(mGood.getId(),
                        SPUtils.getUser(this).getId(), product_details_quantity.getValue());
                break;
            case R.id.rl_cart_icon:
                //跳转到购物车页面
                break;
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                //左上角的返回按钮的监听事件
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
