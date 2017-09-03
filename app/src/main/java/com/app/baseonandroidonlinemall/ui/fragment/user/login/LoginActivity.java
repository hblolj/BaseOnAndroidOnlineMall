package com.app.baseonandroidonlinemall.ui.fragment.user.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.AndroidApplication;
import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.bean.User;
import com.app.baseonandroidonlinemall.base.BaseActivity;
import com.app.baseonandroidonlinemall.injector.components.DaggerLoginComponents;
import com.app.baseonandroidonlinemall.injector.modules.LoginModule;
import com.app.baseonandroidonlinemall.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginDataView {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btnLogin)
    TextView btn_login;
    @BindView(R.id.btnForgetPW)
    TextView btnForgetPW;
    @BindView(R.id.btnRegister)
    TextView btnRegister;
    @BindView(R.id.lin_switch)
    LinearLayout lin_switch;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView toolbar_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        toolbar_title.setText("登 录");
        initToolBar(mToolbar, true, "");
        //使用RxBinding 设置按钮的正则表达式验证
//        RxTextView
//                .textChanges(et_username)
//                .filter(new Predicate<CharSequence>() {
//                    @Override
//                    public boolean test(CharSequence charSequence) throws Exception {
//                        //使用正则表达式
//                        return false;
//                    }
//                })
//                .subscribe(new Consumer<CharSequence>() {
//                    @Override
//                    public void accept(CharSequence charSequence) throws Exception {
//
//                    }
//                });
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void initInjector() {
        DaggerLoginComponents.builder()
                .applicationComponents(AndroidApplication.getApplicationComponents())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void updateViews(boolean isRefresh) {

    }

    public static void launch(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.btnLogin, R.id.btnForgetPW, R.id.btnRegister})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnLogin:
                //登录
//                String username = et_username.getText().toString();
//                String password = et_password.getText().toString();
                //使用RxBinding判断用户名与密码都不为空，或者指定正则表达式后，登录按钮才为可点击状态
                mPresenter.login(et_username.getText().toString(), et_password.getText().toString());
                break;
            case R.id.btnForgetPW:
                //忘记密码 跳转到找回密码页面
                break;
            case R.id.btnRegister:
                //注册 跳转到注册页面
                break;
        }
    }

    @Override
    public void loadData(User data) {

    }

    @Override
    public void loadMoreData(User data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void loadUser(User user) {
        //登录成功后返回的User实体，判断是否为NULL，存储到本地，使用ShredPrefrens
        //在Presenter中已经过滤过了，这里的User是成功返回的
        //登陆成功，跳转到MineFragment
        SPUtils.saveUser(user, this);
        LoginActivity.this.finish();
    }

    @Override
    public void showError(String error) {
        //登录失败留在本页面，显示错误
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
