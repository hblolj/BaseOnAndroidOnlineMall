package com.app.baseonandroidonlinemall.injector.modules;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.app.baseonandroidonlinemall.adapter.HotGridViewAdapter;
import com.app.baseonandroidonlinemall.adapter.HotRecyclerViewAdapter;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.other.OtherFragment;
import com.app.baseonandroidonlinemall.ui.fragment.home.other.OtherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/10.
 */
@Module
public class OtherModule {

    @LayoutRes
    private final int resLayoutId;

    private final OtherFragment mOtherFragment;

    private final String tagId;

    private Context mContext;

    public OtherModule(int resLayoutId, OtherFragment otherFragment, String id, Context context) {
        this.resLayoutId = resLayoutId;
        mOtherFragment = otherFragment;
        tagId = id;
        mContext = context;
    }

    //提供Presenter
    @PerFragment
    @Provides
    public IBasePresenter provideOtherPresenter(){
        return new OtherPresenter(mOtherFragment, tagId);
    }

    @PerFragment
    @Provides
    public HotRecyclerViewAdapter provideAdapter(){
        return new HotRecyclerViewAdapter(resLayoutId);
    }

    @PerFragment
    @Provides
    public HotGridViewAdapter provideHotGridViewAdapter(){
        return new HotGridViewAdapter(mContext);
    }
}
