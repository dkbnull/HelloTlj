package cn.wbnull.hellotlj.activity;

import android.os.Bundle;

import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.presenter.LoginPresenter;
import cn.wbnull.hellotlj.view.ILoginView;

/**
 * 登录界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_login)
public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
