package cn.wbnull.hellotlj.model;

import java.util.List;

import cn.wbnull.hellotlj.model.game.GameStartResponseData;
import cn.wbnull.hellotlj.model.user.RegisterResponseData;
import lombok.Data;

/**
 * 游戏界面玩家信息model
 *
 * @author dukunbiao(null)  2020-02-07
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class GameItemModel {

    private String userId;
    private String nickname;
    private List<String> pokers;

    public String getPokersFormat() {
        return pokers == null ? "" : pokers.toString();
    }

    public static GameItemModel build(RegisterResponseData responseData) {
        GameItemModel gameItemModel = new GameItemModel();
        gameItemModel.userId = responseData.getUserId();
        gameItemModel.nickname = responseData.getNickname();

        return gameItemModel;
    }

    public static GameItemModel build(GameStartResponseData gameData) {
        GameItemModel gameItemModel = new GameItemModel();
        gameItemModel.userId = gameData.getUserId();
        gameItemModel.pokers = gameData.getPokers();

        return gameItemModel;
    }
}
