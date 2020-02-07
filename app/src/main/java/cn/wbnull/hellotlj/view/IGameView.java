package cn.wbnull.hellotlj.view;

import cn.wbnull.hellotlj.model.GameItemModel;

/**
 * 游戏界面 View
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public interface IGameView extends IBaseView {

    void showBtnStart();

    void updateNowUser(GameItemModel gameItemModel);

    void addGameUser(GameItemModel gameItemModel);
}
