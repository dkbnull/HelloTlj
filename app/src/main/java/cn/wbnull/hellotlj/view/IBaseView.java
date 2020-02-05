package cn.wbnull.hellotlj.view;

import android.content.Context;

import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.boot.SingleCallback;

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

    /**
     * 提示框
     */
    void showHintDialog(String msg);

    /**
     * 提示框
     */
    void showHintDialog(String msg, SingleCallback callback);

    /**
     * 对话框
     */
    void showHintConfirm(String msg, GlobalCallback<String> callback);
}
