package com.app.baseonandroidonlinemall.ui.fragment.home;

import com.app.baseonandroidonlinemall.api.bean.HomeData;
import com.app.baseonandroidonlinemall.base.IBaseView;

import java.util.List;

/**
 * Created by hblolj on 2017/5/9.
 * V层接口
 * 主页接口
 */

public interface IHomeDataView extends IBaseView{

    void loadData(List<HomeData> datas);
}
