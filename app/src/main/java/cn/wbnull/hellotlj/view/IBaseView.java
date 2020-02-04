package cn.wbnull.hellotlj.view;

import android.content.Context;

/**
 * Mvp BaseView
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public interface IBaseView {

    /**
     * 获取Context
     *
     * @return
     */
    Context getContext();

    /**
     * 展示等待框
     */
    void showLoadingDialog();

    /**
     * 隐藏等待框
     */
    void hideLoadingDialog();
}
