package com.app.baseonandroidonlinemall.ui.fragment.cart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.CartItemRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.api.bean.User;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.base.BaseFragment;
import com.app.baseonandroidonlinemall.injector.components.DaggerCartComponents;
import com.app.baseonandroidonlinemall.injector.modules.CartModule;
import com.app.baseonandroidonlinemall.ui.fragment.order.OrderActivity;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.ProductDetailActivity;
import com.app.baseonandroidonlinemall.utils.MyDecoration;
import com.app.baseonandroidonlinemall.utils.SPUtils;
import com.app.baseonandroidonlinemall.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hblolj on 2017/5/6.
 */

public class CartFragment extends BaseFragment<CartPresenter> implements ICartDataView{

    private static final String USER_ID = "userId";

    @BindView(R.id.rl_cart_list)
    RecyclerView rl_cart_list;
    @Inject
    CartItemRecyclerViewAdapter mAdapter;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_cart_bottom)
    LinearLayout ll_cart_bottom;
    @BindView(R.id.ll_cart_no_user_view)
    LinearLayout ll_cart_no_user_view;
    //全选按钮
    @BindView(R.id.cb_cart_all)
    CheckBox cb_cart_all;
    //结算
    @BindView(R.id.tv_cart_buy_or_del)
    TextView tv_cart_buy_or_del;
    //总价
    @BindView(R.id.tv_cart_Allprice)
    TextView tv_cart_Allprice;
    //总重量
    @BindView(R.id.tv_cart_Allweight)
    TextView tv_cart_Allweight;
    //总运费
    @BindView(R.id.tv_cart_Allshipprice)
    TextView tv_cart_Allshipprice;

    private String userId;

    public static CartFragment newInstance(Context context) {
        User user = SPUtils.getUser(context);
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        if (user != null){
            args.putString(USER_ID, user.getId());
        }else {
            args.putString(USER_ID, "");
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.checkUserStatus(mContext);
        mPresenter.getData(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initViews() {
        tv_title.setText("我的购物车");
        //ToolBar右侧需要添加移除按钮 或者考虑设置Adapter的Item长按删除
        initToolBar(mToolbar, true, "");
        rl_cart_list.setLayoutManager(new LinearLayoutManager
                (mContext, LinearLayoutManager.VERTICAL, false));
        rl_cart_list.setAdapter(mAdapter);
        rl_cart_list.addItemDecoration(new MyDecoration(mContext, MyDecoration.VERTICAL_LIST));
//        mAdapter.setEmptyView(R.layout.emptyview);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CartItem item = (CartItem) adapter.getItem(position);
                switch (view.getId()){
                    case R.id.item_listview_cart_title:
                        //购物项标题点击事件 跳转到商品详情页面
                        ProductDetailActivity.launch(mContext, item.getGoodsid());
                        break;
                    case R.id.cb_choice:
                        //购物项前面的CheckBox的点击事件 将该购物项加入或移除 选中集合
                        CheckBox checkBox = (CheckBox) view;
                        //点击后的状态
                        if (checkBox.isChecked()){
                            //点击后是选中状态，证明之前是未选中状态
                            ((CartItemRecyclerViewAdapter) adapter).ids.add(item.getGoodsid());
                            ((CartItemRecyclerViewAdapter) adapter).cartItems.add(item);
                        }else {
                            //点击后是未选中状态，证明之前是选中状态
                            ((CartItemRecyclerViewAdapter) adapter).ids.remove(item.getGoodsid());
                            ((CartItemRecyclerViewAdapter) adapter).cartItems.remove(item);
                        }
                        updateInfo();
                        break;
                    case R.id.item_listview_cart_count:
                        //购物项的数量的点击事件 弹出Dialog修改商品数量
                    case R.id.item_listview_cart_iconedit:
                        //购物项数量修改图标点击事件 弹出Dialog修改商品数量
                        initDialog(item, (TextView) adapter.getViewByPosition(rl_cart_list, position, R.id.item_listview_cart_count));
                        break;
                }
            }
        });
    }

    /**
     * 初始化数量修改对话框
     */
    private void initDialog(final CartItem item, final TextView tv_num) {

        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);
        //输入修改数量的EditText
        final EditText editText = (EditText) dialog.findViewById(R.id.editText1);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((EditText) v).selectAll();
                }

            }
        });
        editText.setText(String.format("%d", Integer.parseInt(item.getQuantity())));
        //确定修改按钮
        Button button = (Button) dialog.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newNum = editText.getText().toString();
                if (newNum != null && newNum.length() > 0) {
                    int n = Integer.parseInt(newNum);

                    if (n > 0) {
                        tv_num.setText(String.format("数量：%d", n));
                        item.setQuantity(n+"");
                        item.setSubtotal((Integer.parseInt(item.getCurrprice()) * n) + "");
                       //访问接口 修改购物项数量  购物项Id + 修改后的数量
                        mPresenter.updateCartItemNumber(item.getId(), item.getQuantity());

                        Utils.hideSoftInput(mContext, editText.getWindowToken());

                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }

    @Override
    public void updateViews(boolean isRefresh) {
        //根据传递进来的userId 获取到所有的购物项
        mPresenter.getData(isRefresh);
    }

    @Override
    public void initInjector() {
        userId = getArguments().getString(USER_ID);
        DaggerCartComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .cartModule(new CartModule(this, userId, R.layout.item_cartitem_recyclerview))
                .build()
                .inject(this);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadData(List<CartItem> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreData(List<CartItem> data) {

    }

    @Override
    public void loadNoData() {

    }

    /**
     * 设置购物车页面为用户未登录状态
     */
    @Override
    public void setViewNoUser() {
        //隐藏RecyclerView、隐藏底部结算栏、显示未登录画面
        rl_cart_list.setVisibility(View.GONE);
        ll_cart_bottom.setVisibility(View.GONE);
        ll_cart_no_user_view.setVisibility(View.VISIBLE);
    }

    /**
     * 设置购物车页面为用户登录状态
     */
    @Override
    public void setViewUser() {
        rl_cart_list.setVisibility(View.VISIBLE);
        ll_cart_bottom.setVisibility(View.VISIBLE);
        ll_cart_no_user_view.setVisibility(View.GONE);
    }

    @OnClick({R.id.cb_cart_all, R.id.tv_cart_buy_or_del})
    void onClick(View view){
        switch (view.getId()){
            case R.id.cb_cart_all:
                //全选
                if (((CheckBox) view).isChecked()){
                    //正选
//                    Toast.makeText(mContext, "true", Toast.LENGTH_SHORT).show();
                    mAdapter.selectAll();
                }else {
                    //反选
//                    Toast.makeText(mContext, "false", Toast.LENGTH_SHORT).show();
                    mAdapter.deleteAll();
                }
                updateInfo();
                break;
            case R.id.tv_cart_buy_or_del:
                //结算
                OrderActivity.launch(mContext, mAdapter.cartItems, Integer.parseInt(userId));
                break;
        }
    }

    /**
     * 随着勾选购物项，更新底部总价 数量
     */
    public void updateInfo(){
        List<CartItem> items = mAdapter.cartItems;
        float subTotal = 0;
        for (CartItem c : items){
            String s = c.getSubtotal();
            float f = Float.parseFloat(s);
            subTotal += f;
        }
        tv_cart_buy_or_del.setText("去结算(" + items.size() + ")");
        tv_cart_Allprice.setText("商品合计：￥" + subTotal);
    }
}
