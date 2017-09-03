package com.app.baseonandroidonlinemall.injector.modules;

import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.PerActivity;
import com.app.baseonandroidonlinemall.ui.fragment.user.login.LoginActivity;
import com.app.baseonandroidonlinemall.ui.fragment.user.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hblolj on 2017/5/14.
 */

@Module
public class LoginModule {

    private final LoginActivity mLoginActivity;

    public LoginModule(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    @PerActivity
    @Provides
    public LoginPresenter provideLoginPresenter(){
        return new LoginPresenter(mLoginActivity);
    }
}
