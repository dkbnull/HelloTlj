package cn.wbnull.hellotlj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.config.AppConfig;
import cn.wbnull.hellotlj.presenter.LoginPresenter;
import cn.wbnull.hellotlj.tool.CommonTools;
import cn.wbnull.hellotlj.view.ILoginView;

/**
 * 登录界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_login)
public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.loginLinearRegister)
    public LinearLayout loginLinearRegister;

    @BindView(R.id.loginLinearLogin)
    public LinearLayout loginLinearLogin;

    @BindView(R.id.loginEtUser)
    public EditText loginEtUser;

    @BindView(R.id.loginEtPwd)
    public EditText loginEtPwd;

    @BindView(R.id.loginEtNickName)
    public EditText loginEtNickName;

    @BindView(R.id.loginEtUserName)
    public EditText loginEtUserName;

    @BindView(R.id.loginEtPwd1)
    public EditText loginEtPwd1;

    @BindView(R.id.loginEtPwd2)
    public EditText loginEtPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    private void init() {
        if (AppConfig.hasLogin()) {
            CommonTools.showToastLong("登录成功");
            CommonTools.startNewActivity(MenuActivity.class);
        }
    }

    @OnClick(R.id.loginBtnLogin)
    public void onClickLogin() {
        mPresenter.login(loginEtUser.getText().toString(), loginEtPwd.getText().toString());
    }

    @OnClick(R.id.loginBtnRegister)
    public void onClickRegister() {
        mPresenter.register(loginEtNickName.getText().toString(),
                loginEtUserName.getText().toString(),
                loginEtPwd1.getText().toString(), loginEtPwd2.getText().toString());
    }

    @OnClick(R.id.loginBtnBack)
    public void onClickBack() {
        loginLinearRegister.setVisibility(View.GONE);
        loginLinearLogin.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.loginTvRegister)
    public void onClickToRegister() {
        loginLinearLogin.setVisibility(View.GONE);
        loginLinearRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginSuccess() {
        CommonTools.startNewActivity(MenuActivity.class);
    }
}
