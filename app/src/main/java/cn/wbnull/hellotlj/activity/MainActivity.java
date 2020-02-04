package cn.wbnull.hellotlj.activity;

import android.content.Intent;
import android.os.Bundle;

import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.presenter.MainPresenter;
import cn.wbnull.hellotlj.view.IMainView;

/**
 * 主界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_main)
public class MainActivity extends BaseMvpActivity<IMainView, MainPresenter> implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
