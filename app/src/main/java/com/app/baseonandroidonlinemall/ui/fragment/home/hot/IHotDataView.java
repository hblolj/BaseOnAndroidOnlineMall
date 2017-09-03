package com.app.baseonandroidonlinemall.ui.fragment.home.hot;

import com.app.baseonandroidonlinemall.api.bean.HotData;
import com.app.baseonandroidonlinemall.base.ILoadDataView;

/**
 * Created by hblolj on 2017/5/9.
 */

public interface IHotDataView extends ILoadDataView<HotData>{

    public void loadAdData(HotData data);
}
