package com.app.baseonandroidonlinemall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.utils.EmptyLayout;
import com.app.baseonandroidonlinemall.utils.SwipeRefreshHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hblolj on 2017/4/13.
 * 基类Activity
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView, EmptyLayout.OnRetryListener {

    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;

    @Nullable
    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefresh;

    @Inject
    protected T mPresenter;

    /**
     * 绑定布局文件
      * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化视图控件
     */
    public abstract void initViews();

    /**
     * 初始化事件
     */
    public abstract void initListener();

    /**
     * Dagger注入
     */
    protected abstract void initInjector();

    /**
     * 更新视图控件
     * @param isRefresh
     */
    public abstract void updateViews(boolean isRefresh);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initInjector();
        initViews();
        initListener();
        initSwipeRefresh();
        updateViews(false);
        ActivityCollctor.addActivity(this);
    }

    protected void initSwipeRefresh(){
        if (mSwipeRefresh != null) {
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews(true);
                }
            });
        }
    };

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    @Override
    public void finishRefresh() {
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    /**
     * 初始化ToolBar
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle){
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ActivityCollctor.sActivities.contains(this)) {
            ActivityCollctor.removeActivity(this);
        }
    }
}
