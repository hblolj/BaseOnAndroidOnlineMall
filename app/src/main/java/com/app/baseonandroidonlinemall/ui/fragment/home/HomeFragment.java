package com.app.baseonandroidonlinemall.ui.fragment.home;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.HomeViewPagerAdapter;
import com.app.baseonandroidonlinemall.api.bean.HomeData;
import com.app.baseonandroidonlinemall.base.BaseFragment;
import com.app.baseonandroidonlinemall.base.ILoadDataView;
import com.app.baseonandroidonlinemall.injector.components.DaggerHomeComponents;
import com.app.baseonandroidonlinemall.injector.modules.HomeModule;
import com.app.baseonandroidonlinemall.ui.fragment.home.hot.HotFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.other.OtherFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hblolj on 2017/5/6.
 * 四个主界面之一
 */

public class HomeFragment extends BaseFragment implements IHomeDataView{

    public static final String TAG = "HomeFragment";

    @BindView(R.id.mainTop)
    LinearLayout ll_mainTop;
    @BindView(R.id.mainSearchBar)
    LinearLayout ll_mainSearchBar;
    @BindView(R.id.user_image)
    CircleImageView civ_userImage;
    @BindView(R.id.index_search_edit)
    EditText et_search;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_home)
    ViewPager vp_home;
    @Inject
    HomeViewPagerAdapter mViewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        mTabLayout.setTabTextColors(Color.BLACK, Color.BLUE);
        mTabLayout.setSelectedTabIndicatorColor(Color.RED);
    }

    @Override
    public void initListener() {
        mTabLayout.setupWithViewPager(vp_home);
        vp_home.setAdapter(mViewPagerAdapter);
    }

    /**
     * 获取数据，标明是否是刷新获取
     * @param isRefresh
     */
    @Override
    public void updateViews(boolean isRefresh) {
        //请求数据
        mPresenter.getData(false);
    }

    @Override
    public void initInjector() {
        //注入
        DaggerHomeComponents
                .builder()
                .homeModule(new HomeModule(this))
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .build()
                .inject(this);
    }

    @Override
    public void loadData(List<HomeData> datas) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> mTitles = new ArrayList<>();
        for (HomeData data : datas){
            mTitles.add(data.getTitle());
            if (data.getTagId().equals("0")){
                HotFragment fragment = HotFragment.newInstance(data.getTitle());
                fragments.add(fragment);
            }else {
                OtherFragment fragment = OtherFragment.newInstance(data.getTagId(), data.getTitle());
                fragments.add(fragment);
            }
        }
        mViewPagerAdapter.setItems(fragments, mTitles);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void showError(String error) {

    }
}
