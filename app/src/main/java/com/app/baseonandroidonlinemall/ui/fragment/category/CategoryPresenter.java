package com.app.baseonandroidonlinemall.ui.fragment.category;

import com.app.baseonandroidonlinemall.api.bean.CategoryData;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.base.ILoadDataView;
import com.app.baseonandroidonlinemall.model.CategoryModel;

import java.util.List;

/**
 * Created by hblolj on 2017/5/8.
 * 分类页面 Presenter
 *
 * 获取所有Big分类 和其对应的子分类项
 */

public class CategoryPresenter implements IBasePresenter {

    //View层的引用
    private ICategoryDataView mFragment;
    //Model层的引用
    private CategoryModel mCategoryModel;

    public CategoryPresenter(ICategoryDataView fragment) {
        mFragment = fragment;
        mCategoryModel = new CategoryModel();
    }

    @Override
    public void getData(boolean isRefresh) {
        //获取到分类数据，调用View层引用的方法，将数据传回C层
        mFragment.showLoading();
        List<CategoryData> categoryData = mCategoryModel.getCategoryData();
        mFragment.loadData(categoryData);
        mFragment.hideLoading();
    }

    @Override
    public void getMoreData() {

    }
}
