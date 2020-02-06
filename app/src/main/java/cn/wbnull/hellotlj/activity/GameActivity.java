package cn.wbnull.hellotlj.activity;

import android.os.Bundle;

import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.presenter.GamePresenter;
import cn.wbnull.hellotlj.view.IGameView;

/**
 * 游戏界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_game)
public class GameActivity extends BaseMvpActivity<IGameView, GamePresenter> implements IGameView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.init();
    }

    @Override
    protected GamePresenter createPresenter() {
        return new GamePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.destroy();
    }
}
