package cn.wbnull.hellotlj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.adapter.GameItemAdapter;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;
import cn.wbnull.hellotlj.model.GameItemModel;
import cn.wbnull.hellotlj.presenter.GamePresenter;
import cn.wbnull.hellotlj.view.IGameView;
import cn.wbnull.helloutil.util.StringUtils;

/**
 * 游戏界面
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
@ActivityLayoutInject(R.layout.activity_game)
public class GameActivity extends BaseMvpActivity<IGameView, GamePresenter> implements IGameView {

    @BindView(R.id.gameRvUser)
    public RecyclerView gameRvUser;

    @BindView(R.id.gameBtnStart)
    public Button gameBtnStart;

    @BindView(R.id.gameTvUser)
    public TextView gameTvUser;

    @BindView(R.id.gameTvPoker)
    public TextView gameTvPoker;

    private GameItemAdapter gameItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter();
        mPresenter.init();
        mPresenter.info();
    }

    @Override
    protected GamePresenter createPresenter() {
        return new GamePresenter();
    }

    private void initAdapter() {
        gameRvUser.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        gameItemAdapter = new GameItemAdapter(gameRvUser);
        gameRvUser.setAdapter(gameItemAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.unregister();
    }

    @OnClick(R.id.gameBtnStart)
    public void onClickStart() {
        mPresenter.start();
    }

    @Override
    public void showBtnStart() {
        gameBtnStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateNowUser(GameItemModel gameItemModel) {
        if (!StringUtils.isEmpty(gameItemModel.getNickname())) {
            gameTvUser.setText(gameItemModel.getNickname());
        }

        gameTvPoker.setText(gameItemModel.getPokersFormat());
    }

    @Override
    public void addGameUser(GameItemModel gameItemModel) {
        gameItemAdapter.addItem(gameItemModel);
    }

    @Override
    public void updateGameUser(GameItemModel gameItemModel) {
        gameItemAdapter.updateItem(gameItemModel);
    }
}
