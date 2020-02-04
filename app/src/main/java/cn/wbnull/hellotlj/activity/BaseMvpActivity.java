package cn.wbnull.hellotlj.activity;

import android.content.Context;
import android.os.Bundle;

import cn.wbnull.hellotlj.presenter.BasePresenter;
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

    }

    @Override
    public void hideLoadingDialog() {

    }
}
