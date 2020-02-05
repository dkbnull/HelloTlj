package cn.wbnull.hellotlj.activity;

import android.content.Context;
import android.os.Bundle;

import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.boot.SingleCallback;
import cn.wbnull.hellotlj.presenter.BasePresenter;
import cn.wbnull.hellotlj.tool.CommonTools;
import cn.wbnull.hellotlj.view.IBaseView;

/**
 * Mvp BaseMvpActivity
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public abstract class BaseMvpActivity<V extends IBaseView, T extends BasePresenter<V>> extends BaseActivity implements IBaseView {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    protected abstract T createPresenter();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoadingDialog() {
        LoadingDialog.showLoading();
    }

    @Override
    public void hideLoadingDialog() {
        LoadingDialog.dismissLoading();
    }

    @Override
    public void showHintDialog(String msg) {
        CommonTools.showInfoDialog(msg);
    }

    @Override
    public void showHintDialog(String msg, SingleCallback callback) {
        CommonTools.showInfoDialog(msg, (dialog, which) -> {
            callback.onSuccess("");
        });
    }

    @Override
    public void showHintConfirm(String msg, GlobalCallback<String> callback) {
        CommonTools.showConfirmDialog(msg, (dialog, which) -> {
            callback.onSuccess("");
        }, (dialog, which) -> {
            callback.onFailure("");
        });
    }
}
