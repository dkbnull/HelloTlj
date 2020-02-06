package cn.wbnull.hellotlj.activity;

import android.os.Bundle;

import butterknife.OnClick;
import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.presenter.MenuPresenter;
import cn.wbnull.hellotlj.tool.CommonTools;
import cn.wbnull.hellotlj.view.IMenuView;

/**
 * 菜单界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_menu)
public class MenuActivity extends BaseMvpActivity<IMenuView, MenuPresenter> implements IMenuView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MenuPresenter createPresenter() {
        return new MenuPresenter();
    }

    @OnClick(R.id.menuBtnCreate)
    public void onClickCreate() {
        showHintDialog("直接点击加入房间");
    }

    @OnClick(R.id.menuBtnJoin)
    public void onClickJoin() {
        mPresenter.join();
    }

    @Override
    public void joinSuccess() {
        CommonTools.startNewActivity(GameActivity.class);
    }
}
