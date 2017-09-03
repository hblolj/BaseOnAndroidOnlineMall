package com.app.baseonandroidonlinemall.ui.fragment.category;

import com.app.baseonandroidonlinemall.api.bean.CategoryData;
import com.app.baseonandroidonlinemall.base.ILoadDataView;

import java.util.List;

/**
 * Created by hblolj on 2017/5/9.
 *
 * 基于CategoryFragment封装的接口，给P层调用
 */

public interface ICategoryDataView extends ILoadDataView<List<CategoryData>> {


}
