package cn.wbnull.hellotlj.model.game;

import java.util.List;

import lombok.Data;

/**
 * 开始游戏接口   后台返回信息
 *
 * @author dukunbiao(null)  2020-02-07
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class GameStartResponseData {

    private String userId;
    private List<String> pokers;

    public static GameStartResponseData build(String userId, List<String> pokers) {
        GameStartResponseData appResponseData = new GameStartResponseData();
        appResponseData.userId = userId;
        appResponseData.pokers = pokers;

        return appResponseData;
    }
}
