package com.app.baseonandroidonlinemall.ui.fragment.home.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.HotRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.api.bean.HotData;
import com.app.baseonandroidonlinemall.api.bean.hot.spceialAdItem;
import com.app.baseonandroidonlinemall.base.BaseFragment;
import com.app.baseonandroidonlinemall.injector.components.DaggerHotComponents;
import com.app.baseonandroidonlinemall.injector.modules.HotModule;
import com.app.baseonandroidonlinemall.ui.fragment.product.detail.ProductDetailActivity;
import com.app.baseonandroidonlinemall.ui.fragment.product.list.ProductListActivity;
import com.app.baseonandroidonlinemall.utils.SliderHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.SliderLayout;


import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by hblolj on 2017/5/7.
 */

public class HotFragment extends BaseFragment implements IHotDataView{

    @BindView(R.id.rl_hot)
    RecyclerView rl_hot;
    @Inject
    HotRecyclerViewAdapter mHotRecyclerViewAdapter;

    public String title;
    private SliderLayout mAdSlider;

    public static HotFragment newInstance(String title) {

        HotFragment fragment = new HotFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            title = getArguments().getString("title");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initViews() {
        rl_hot.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rl_hot.setAdapter(mHotRecyclerViewAdapter);
        View view = LayoutInflater.from(mContext).inflate(R.layout.head_hot_list, null);
        mAdSlider = ((SliderLayout) view.findViewById(R.id.slideLayout));
        mHotRecyclerViewAdapter.addHeaderView(view);
    }

    @Override
    public void initListener() {
        mHotRecyclerViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                spceialAdItem item = (spceialAdItem) adapter.getItem(position);
                switch (view.getId()){
                    case R.id.iv_good_group_big_picture:
                        ProductListActivity.launch(mContext, item.getId());
                        break;
                }
//                ProductDetailActivity.launch(mContext, item.getId());
//                Toast.makeText(mContext, position + " : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void initInjector() {
        DaggerHotComponents.builder()
                .hotModule(new HotModule(R.layout.item_hot_recyclerview, this))
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .build()
                .inject(this);
    }

    @Override
    public void loadData(HotData data) {
        mHotRecyclerViewAdapter.setNewData(data.getSpceialAdList());
        SliderHelper.initAdSlider(mContext, mAdSlider, data.getFocusAdList());
    }

    @Override
    public void loadMoreData(HotData data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void loadAdData(HotData data) {
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
}
