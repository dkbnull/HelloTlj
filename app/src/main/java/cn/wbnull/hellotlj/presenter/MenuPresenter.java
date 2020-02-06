package cn.wbnull.hellotlj.presenter;

import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.model.GameModel;
import cn.wbnull.hellotlj.model.game.GameJoinResponseData;
import cn.wbnull.hellotlj.service.game.GameService;
import cn.wbnull.hellotlj.view.IMenuView;

/**
 * 菜单界面 Presenter
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public class MenuPresenter extends BasePresenter<IMenuView> {

    public void join() {
        mView.showLoadingDialog();
        GameService.join("1000", new GlobalCallback<GameJoinResponseData>() {
            @Override
            public void onSuccess(GameJoinResponseData registerResponseData) {
                mView.hideLoadingDialog();
                GameModel.setTableId(registerResponseData.getGametable().getTableId());
                mView.joinSuccess();
            }

            @Override
            public void onFailure(String msg) {
                mView.showHintDialog(msg);
                mView.hideLoadingDialog();
            }
        });
    }
}
