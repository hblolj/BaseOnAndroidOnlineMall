package com.app.baseonandroidonlinemall.ui.fragment.mine;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.bean.User;
import com.app.baseonandroidonlinemall.base.BaseFragment;
import com.app.baseonandroidonlinemall.base.IBasePresenter;
import com.app.baseonandroidonlinemall.injector.components.DaggerMineComponents;
import com.app.baseonandroidonlinemall.injector.modules.MineModule;
import com.app.baseonandroidonlinemall.ui.fragment.order.list.OrderListActivity;
import com.app.baseonandroidonlinemall.ui.fragment.user.login.LoginActivity;
import com.app.baseonandroidonlinemall.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hblolj on 2017/5/6.
 */

public class MineFragment extends BaseFragment<IBasePresenter> {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    //登录提示
    @BindView(R.id.ll_main_user_login)
    LinearLayout ll_login;
    //用户信息显示
    @BindView(R.id.ll_main_user_param)
    LinearLayout ll_main_user_param;
    //用户名
    @BindView(R.id.tv_main_user_name)
    TextView tv_main_user_name;
    //用户积分
    @BindView(R.id.tv_main_user_score)
    TextView tv_main_user_score;
    //用户等级
    @BindView(R.id.iv_main_user_level)
    TextView iv_main_user_level;
    //注销按钮
    @BindView(R.id.tv_main_user_logout)
    TextView tv_main_user_logout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews() {
        initToolBar(mToolbar, false, "");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onResume() {
        super.onResume();
        initUserStates();
    }

    public void initUserStates(){
        User user = SPUtils.getUser(mContext);
        if (user == null){
            //未登录状态
            ll_login.setVisibility(View.VISIBLE);
            tv_main_user_logout.setVisibility(View.GONE);
            ll_main_user_param.setVisibility(View.GONE);
            iv_main_user_level.setVisibility(View.GONE);
        }else {
            //登录状态
            ll_login.setVisibility(View.GONE);
            tv_main_user_logout.setVisibility(View.VISIBLE);
            ll_main_user_param.setVisibility(View.VISIBLE);
            iv_main_user_level.setVisibility(View.VISIBLE);
            iv_main_user_level.setText("Level: " + user.getLevel());
            tv_main_user_name.setText(user.getUsername());
            tv_main_user_score.setText("888");
        }
    }

    @Override
    public void updateViews(boolean isRefresh) {
        //不用获取数据，数据由用户登录后获取
    }

    @Override
    public void initInjector() {
        DaggerMineComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .mineModule(new MineModule(this))
                .build()
                .inject(this);
    }

    @OnClick({R.id.ll_main_user_login, R.id.tv_main_user_logout, R.id.lv_main_user_wodedingdan})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.ll_main_user_login:
                //点击用户登录，跳转到登录页面
                LoginActivity.launch(mContext);
                break;
            case R.id.tv_main_user_logout:
                SPUtils.setUserNull(mContext);
                initUserStates();
                break;
            case R.id.lv_main_user_wodedingdan:
                User user = SPUtils.getUser(mContext);
                if (user == null){
                    LoginActivity.launch(mContext);
                }else {
                    String id = user.getId();
                    OrderListActivity.launch(mContext, id);
                }
                break;
        }
    }

    @Override
    public void showError(String error) {

    }
}
