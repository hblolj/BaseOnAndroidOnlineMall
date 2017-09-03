package com.app.baseonandroidonlinemall.ui.fragment.home;

import com.app.baseonandroidonlinemall.api.bean.HomeData;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.model.HomeModel;

import java.util.List;

/**
 * Created by hblolj on 2017/5/9.
 */

public class HomePresenter implements IBasePresenter {

    private IHomeDataView mView;
    private HomeModel mHomeModel;

    public HomePresenter(IHomeDataView view) {
        mView = view;
        mHomeModel = new HomeModel();
    }

    @Override
    public void getData(boolean isRefresh) {
        List<HomeData> data = mHomeModel.getData();
        mView.loadData(data);
    }

    @Override
    public void getMoreData() {

    }
}
