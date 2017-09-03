package com.app.baseonandroidonlinemall.injector.modules;

import android.content.Context;

import com.app.baseonandroidonlinemall.AndroidApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/10.
 */

@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;

    public ApplicationModule(AndroidApplication application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }
}
