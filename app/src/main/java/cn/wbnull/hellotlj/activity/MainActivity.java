package cn.wbnull.hellotlj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.presenter.MainPresenter;
import cn.wbnull.hellotlj.tool.CommonTools;
import cn.wbnull.hellotlj.view.IMainView;

/**
 * 主界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_main)
public class MainActivity extends BaseMvpActivity<IMainView, MainPresenter> implements IMainView {

    public CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (hasPermission()) {
            initTimer();
            timeStart();
        }
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    private void initTimer() {
        timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                CommonTools.startNewActivity(LoginActivity.class);
            }
        };
    }

    public void timeStart() {
        new Handler(getMainLooper()).post(() -> timer.start());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (hasPermission()) {
            initTimer();
        }
    }
}
