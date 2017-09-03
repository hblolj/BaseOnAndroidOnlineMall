package com.app.baseonandroidonlinemall.ui.fragment.category;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.adapter.CategoryRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.api.bean.CategoryData;
import com.app.baseonandroidonlinemall.base.BaseFragment;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.base.ILoadDataView;
import com.app.baseonandroidonlinemall.injector.components.DaggerCategoryComponents;
import com.app.baseonandroidonlinemall.injector.modules.CategoryModule;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hblolj on 2017/5/6.
 */

public class CategoryFragment extends BaseFragment<IBasePresenter> implements ICategoryDataView{

    @BindView(R.id.rl_catrgory)
    RecyclerView mRecyclerView;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    private CategoryRecyclerViewAdapter adapter;

    List<CategoryData> mDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void initViews() {
        initToolBar(mToolbar, false, "");
        adapter = new CategoryRecyclerViewAdapter(mDatas, getContext());
    }

    @Override
    public void initListener() {
        adapter.setOnBigPictureClickListener(new CategoryRecyclerViewAdapter.OnBigPictureClickListener() {
            @Override
            public void onBigPictureClick(View view, int position) {
                Toast.makeText(getContext(), "大图" + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemClick(View view, String item) {
                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initInjector() {
        DaggerCategoryComponents
                .builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .categoryModule(new CategoryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void updateViews(boolean isRefresh) {
        //通过P层的引用，发起数去请求
        mPresenter.getData(false);
    }

    @Override
    public void loadData(List<CategoryData> data) {
        adapter.setItems(data);
    }

    @Override
    public void loadMoreData(List<CategoryData> data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void showError(String error) {

    }
}
