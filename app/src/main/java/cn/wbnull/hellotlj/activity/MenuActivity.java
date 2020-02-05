package cn.wbnull.hellotlj.activity;

import android.os.Bundle;

import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.presenter.BasePresenter;
import cn.wbnull.hellotlj.view.IBaseView;

/**
 * 主界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_menu)
public class MenuActivity extends BaseMvpActivity<IBaseView, BasePresenter<IBaseView>> implements IBaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BasePresenter<IBaseView> createPresenter() {
        return new BasePresenter<>();
    }
}
