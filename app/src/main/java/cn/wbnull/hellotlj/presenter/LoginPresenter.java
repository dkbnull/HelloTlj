package cn.wbnull.hellotlj.presenter;

import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.config.AppConfig;
import cn.wbnull.hellotlj.model.user.RegisterResponseData;
import cn.wbnull.hellotlj.service.user.UserService;
import cn.wbnull.hellotlj.view.ILoginView;
import cn.wbnull.helloutil.util.StringUtils;

/**
 * 登录界面 Presenter
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public void register(String nickname, String username, String password, String passwordConfirm) {
        if (!StringUtils.areNotEmpty(nickname, username, password, passwordConfirm)) {
            mView.showHintDialog("注册信息不能为空");
            return;
        }
        if (!password.equals(passwordConfirm)) {
            mView.showHintDialog("两次输入密码不同");
            return;
        }

        mView.showLoadingDialog();
        UserService.register(nickname, username, password, new GlobalCallback<RegisterResponseData>() {
            @Override
            public void onSuccess(RegisterResponseData registerResponseData) {
                AppConfig.setUserId(registerResponseData.getId());
                AppConfig.setNickname(registerResponseData.getNickname());
                AppConfig.setScore(registerResponseData.getScore().toString());
                AppConfig.setWinNum(registerResponseData.getWinNum().toString());
                AppConfig.setFailNum(registerResponseData.getFailNum().toString());
                AppConfig.setDogfallNum(registerResponseData.getDogfallNum().toString());

                mView.showHintDialog("注册成功", (msg) -> {
                    mView.hideLoadingDialog();
                    mView.loginSuccess();
                });
            }

            @Override
            public void onFailure(String msg) {
                mView.showHintDialog(msg);
                mView.hideLoadingDialog();
            }
        });
    }

    public void login(String username, String password) {
        if (!StringUtils.areNotEmpty(username, password)) {
            mView.showHintDialog("用户名或密码不能为空");
            return;
        }

        mView.showLoadingDialog();
        UserService.login(username, password, new GlobalCallback<RegisterResponseData>() {
            @Override
            public void onSuccess(RegisterResponseData registerResponseData) {
                mView.hideLoadingDialog();
                mView.loginSuccess();
            }

            @Override
            public void onFailure(String msg) {
                mView.showHintDialog(msg);
                mView.hideLoadingDialog();
            }
        });
    }
}
