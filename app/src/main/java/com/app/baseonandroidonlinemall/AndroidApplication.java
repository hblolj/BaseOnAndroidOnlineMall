package com.app.baseonandroidonlinemall;

import android.app.Application;
import android.content.Context;

import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.injector.components.ApplicationComponents;
import com.app.baseonandroidonlinemall.injector.components.DaggerApplicationComponents;
import com.app.baseonandroidonlinemall.injector.modules.ApplicationModule;

/**
 * Created by hblolj on 2017/5/10.
 */

public class AndroidApplication extends Application {

    private static AndroidApplication mApplicationInstance;
    private static Context mContext;
    private static ApplicationComponents mApplicationComponents;

    public static ApplicationComponents getApplicationComponents() {
        return mApplicationComponents;
    }

    public static AndroidApplication getInstance() {
        return mApplicationInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponents = DaggerApplicationComponents.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mContext = getApplicationContext();
        mApplicationInstance = this;
        _initConfig();
    }

    private void _initConfig() {
        RetrofitService.init();
    }

    public static Context getContext(){
        return mContext;
    }
}
