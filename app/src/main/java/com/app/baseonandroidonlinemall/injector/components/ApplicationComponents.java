package com.app.baseonandroidonlinemall.injector.components;

import android.content.Context;

import com.app.baseonandroidonlinemall.injector.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/10.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponents {

    //provide
    Context getContext();
}
