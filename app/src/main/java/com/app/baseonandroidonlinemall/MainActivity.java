package com.app.baseonandroidonlinemall;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.baseonandroidonlinemall.base.BaseActivity;
import com.app.baseonandroidonlinemall.base.BaseFragment;
import com.app.baseonandroidonlinemall.ui.fragment.cart.CartFragment;
import com.app.baseonandroidonlinemall.ui.fragment.category.CategoryFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.HomeFragment;
import com.app.baseonandroidonlinemall.ui.fragment.mine.MineFragment;
import com.app.baseonandroidonlinemall.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 程序入口
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_menu_home)
    LinearLayout ll_menu_home;
    @BindView(R.id.ll_menu_category)
    LinearLayout ll_menu_category;
    @BindView(R.id.ll_menu_cart)
    LinearLayout ll_menu_cart;
    @BindView(R.id.ll_menu_mine)
    LinearLayout ll_menu_mime;

    // 界面底部的未选中菜单按钮资源
    private int[] defaultImages = {
            R.drawable.main_home,
            R.drawable.main_category,
            R.drawable.main_cart,
            R.drawable.main_user
    };
    // 界面底部的选中菜单按钮资源
    private int[] selectedImages = {
            R.drawable.main_home_selected,
            R.drawable.main_category_selected,
            R.drawable.main_cart_selected,
            R.drawable.main_user_selected
    };

    private BaseFragment mCurrentFragment;
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private CartFragment mCartFragment;
    private MineFragment mMineFragment;

    //界面底部菜单按钮数量
//    private static final int TAB_COUNT = 4;
//    private LinearLayout[] tabs = new LinearLayout[TAB_COUNT];

    private List<LinearLayout> tabs = new ArrayList<>();
    private FragmentUtil mFragmentUtil;

    int mLastSelectedTab;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mFragmentUtil = new FragmentUtil(this, R.id.show_layout);

        tabs.add(ll_menu_home);
        tabs.add(ll_menu_category);
        tabs.add(ll_menu_cart);
        tabs.add(ll_menu_mime);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    public void updateViews(boolean isRefresh) {
        //初始化默认界面
        selectTab(R.id.ll_menu_home);
        mLastSelectedTab = R.id.ll_menu_home;
    }

    //点击底部导航栏
    @OnClick({R.id.ll_menu_home, R.id.ll_menu_category, R.id.ll_menu_cart, R.id.ll_menu_mine})
    public void clickNavigationBar(View v){
        int vId = v.getId();
        if (mLastSelectedTab != vId){
            selectTab(vId);
            mLastSelectedTab = vId;
        }
    }

    @Override
    public void showError(String error) {

    }

    //根据点击的底部按钮，切换Fragment
    private void selectTab(int tabId) {
        mFragmentUtil.beginTransaction();

        //处理之前选中的Tab detach

        //在处理新选中的Tab
        if (mCurrentFragment != null){
            mCurrentFragment.setUserVisibleHint(false);
        }
        switch (tabId){
            case R.id.ll_menu_home:
                if (mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                }
                mCurrentFragment = mHomeFragment;
                break;
            case R.id.ll_menu_category:
                if (mCategoryFragment == null){
                    mCategoryFragment = new CategoryFragment();
                }
                mCurrentFragment = mCategoryFragment;
                break;
            case R.id.ll_menu_cart:
                if (mCartFragment == null){
                    mCartFragment = CartFragment.newInstance(this);
                }
                mCurrentFragment = mCartFragment;
                break;
            case R.id.ll_menu_mine:
                if (mMineFragment == null){
                    mMineFragment = new MineFragment();
                }
                mCurrentFragment = mMineFragment;
                break;
        }

        //设置导航栏按钮的选中与未选中状态
        for (int i=0; i<tabs.size(); i++){
            LinearLayout ll = tabs.get(i);
            ImageView iv = ((ImageView) ll.getChildAt(0));
            TextView tv = (TextView) ll.getChildAt(1);
            //全部设置为未点击状态
            iv.setImageResource(defaultImages[i]);
            tv.setTextColor(getResources().getColor(R.color.main_normal));
            //将选中的按钮置为点击状态
            if (tabId == ll.getId()){
                iv.setImageResource(selectedImages[i]);
                tv.setTextColor(getResources().getColor(R.color.main_menu_selected));
            }
        }
        mCurrentFragment.setUserVisibleHint(true);
        mFragmentUtil.show(mCurrentFragment);
        mFragmentUtil.commit();
    }
}
