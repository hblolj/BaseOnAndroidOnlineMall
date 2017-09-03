package com.app.baseonandroidonlinemall.injector.components;

import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.injector.modules.LoginModule;
import com.app.baseonandroidonlinemall.ui.fragment.user.login.LoginActivity;

import dagger.Component;

/**
 * Created by hblolj on 2017/5/14.
 */

@PerActivity
@Component(modules = LoginModule.class, dependencies = ApplicationComponents.class)
public interface LoginComponents {

    void inject(LoginActivity activity);
}
