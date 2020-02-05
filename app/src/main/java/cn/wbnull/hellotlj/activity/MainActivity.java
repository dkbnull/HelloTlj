package cn.wbnull.hellotlj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.annotation.NonNull;

import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.presenter.BasePresenter;
import cn.wbnull.hellotlj.tool.CommonTools;
import cn.wbnull.hellotlj.view.IBaseView;

/**
 * 主界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_main)
public class MainActivity extends BaseMvpActivity<IBaseView, BasePresenter<IBaseView>> implements IBaseView {

    public CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTimer();
    }

    @Override
    protected BasePresenter<IBaseView> createPresenter() {
        return new BasePresenter<>();
    }

    private void initTimer() {
        if (!hasPermission()) {
            return;
        }

        timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                CommonTools.startNewActivity(LoginActivity.class);
            }
        };

        new Handler(getMainLooper()).post(() -> timer.start());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        initTimer();
        //防止因为授权完成后全屏失效
        setHideVirtualKey();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        initTimer();
        //防止因为授权完成后全屏失效
        setHideVirtualKey();
    }
}
