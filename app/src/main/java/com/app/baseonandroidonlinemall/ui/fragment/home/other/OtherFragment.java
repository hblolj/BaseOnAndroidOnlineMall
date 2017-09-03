package com.app.baseonandroidonlinemall.ui.fragment.home.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.HotGridViewAdapter;
import com.app.baseonandroidonlinemall.adapter.HotRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.api.bean.OtherData;
import com.app.baseonandroidonlinemall.api.bean.hot.spceialAdItem;
import com.app.baseonandroidonlinemall.base.BaseFragment;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.components.DaggerOtherComponents;
import com.app.baseonandroidonlinemall.injector.modules.OtherModule;
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

public class OtherFragment extends BaseFragment implements IOtherDataView{

    @BindView(R.id.rl_other)
    RecyclerView rl_other;
    @Inject
    HotRecyclerViewAdapter mAdapter;
    @Inject
    HotGridViewAdapter mHotGridViewAdapter;
    GridView mGridView;

    private String tagId;
    public String title;
    private SliderLayout mAdSlider;

    public static OtherFragment newInstance(String tagId, String title) {

        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString("tagId", tagId);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            tagId = getArguments().getString("tagId");
            title = getArguments().getString("title");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    public void initViews() {
        rl_other.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rl_other.setAdapter(mAdapter);
        View view = LayoutInflater.from(mContext).inflate(R.layout.head_other_list, null);
        mAdSlider = (SliderLayout) view.findViewById(R.id.slideLayout);
        mGridView = ((GridView) view.findViewById(R.id.gv_imagebutton));
        mGridView.setAdapter(mHotGridViewAdapter);
        //加载为头布局
        mAdapter.addHeaderView(view);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                spceialAdItem item = (spceialAdItem) adapter.getItem(position);
//                ProductDetailActivity.launch(mContext, item.getId());
//                Toast.makeText(mContext, position + " : " + item.getId(), Toast.LENGTH_SHORT).show();
                switch (view.getId()){
                    case R.id.iv_good_group_big_picture:
                        ProductListActivity.launch(mContext, item.getId());
                        break;
                }
            }
        });
    }

    @Override
    public void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void initInjector() {
        DaggerOtherComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .otherModule(new OtherModule(R.layout.item_hot_recyclerview, this, tagId, mContext))
                .build()
                .inject(this);
    }

    /**
     * 三个加载数据后期应该修改为不同的loadData方法，在Presenter里通过过滤器与转换器进行分别调用
     * @param data
     */
    @Override
    public void loadData(OtherData data) {
        //加载数据到轮播图
        SliderHelper.initAdSlider(mContext, mAdSlider, data.getFocusAdList());
        //加载数据给品牌按钮
//        mHotGridViewAdapter = new HotGridViewAdapter(data.getHotBrandList(), mContext);
//        mGridView.setAdapter(mHotGridViewAdapter);
        mHotGridViewAdapter.setNewData(data.getHotBrandList());
        //加载数据给RecyclerView
        mAdapter.setNewData(data.getSpceialAdList());

    }

    @Override
    public void loadMoreData(OtherData data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdSlider != null){
            mAdSlider.startAutoCycle();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdSlider != null){
            mAdSlider.stopAutoCycle();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
}
