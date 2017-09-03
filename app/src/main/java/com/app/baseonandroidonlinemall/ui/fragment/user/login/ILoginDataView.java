package com.app.baseonandroidonlinemall.ui.fragment.user.login;

import com.app.baseonandroidonlinemall.api.bean.User;
import com.app.baseonandroidonlinemall.base.ILoadDataView;

/**
 * Created by hblolj on 2017/5/14.
 */

public interface ILoginDataView extends ILoadDataView<User> {

    public void loadUser(User user);
}
