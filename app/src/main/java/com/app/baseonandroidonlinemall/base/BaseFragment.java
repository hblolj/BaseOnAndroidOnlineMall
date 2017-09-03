package com.app.baseonandroidonlinemall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.utils.EmptyLayout;
import com.app.baseonandroidonlinemall.utils.SwipeRefreshHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hblolj on 2017/4/13.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView, EmptyLayout.OnRetryListener {

//    private boolean isVisible = false;
//    private boolean isInitView = false;
//    private boolean isFirstLoad = true;

    private View convertView;

    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @Nullable
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @Inject
    protected T mPresenter;

    protected Context mContext;
    //缓存Fragment view;
    private View mRootView;
    //是否加载过
    private boolean mIsMulti = false;

    /**
     * 绑定布局
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
     * 更新视图控件
     * @param isRefresh
     */
    public abstract void updateViews(boolean isRefresh);

    /**
     * Dagger注入
     */
    public abstract void initInjector();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
            initInjector();
            initViews();
            initListener();
            initSwipeRefresh();
        }
        //断绝关系
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView !=null && !mIsMulti){
            //用户可见，缓存View不为Null,?
            mIsMulti = true;
            updateViews(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView !=null && !mIsMulti){
            mIsMulti = true;
            updateViews(false);
        }else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout != null){
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null){
            mEmptyLayout.hide();
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);
            SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);
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
        ((BaseActivity) getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }

    private void initSwipeRefresh(){
        if (mSwipeRefresh != null){
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews(true);
                }
            });
        }
    }
}
