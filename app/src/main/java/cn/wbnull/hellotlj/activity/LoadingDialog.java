package cn.wbnull.hellotlj.activity;

import android.content.Context;
import android.os.Bundle;

import cn.wbnull.hellotlj.App;
import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;

/**
 * 加载状态框
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.dialog_loading)
public class LoadingDialog extends BaseDialog {

    private static LoadingDialog loadingDialog;

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
    }

    public static void showLoading() {
        if (LoadingDialog.loadingDialog == null) {
            LoadingDialog.loadingDialog = new LoadingDialog(App.getContext());
            //loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        loadingDialog.show();
    }

    public static void dismissLoading() {
        if (LoadingDialog.loadingDialog == null) {
            return;
        }

        loadingDialog.dismiss();
        loadingDialog = null;
    }
}
