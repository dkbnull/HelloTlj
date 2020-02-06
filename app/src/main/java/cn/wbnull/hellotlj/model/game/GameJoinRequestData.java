package cn.wbnull.hellotlj.model.game;

import cn.wbnull.hellotlj.config.AppConfig;
import cn.wbnull.hellotlj.model.AppRequest;
import lombok.Data;

/**
 * 加入游戏接口   前台请求信息
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class GameJoinRequestData {

    private String tableId;
    private String userId;

    public static AppRequest<GameJoinRequestData> build(String tableId) {
        GameJoinRequestData requestData = new GameJoinRequestData();
        requestData.tableId = tableId;
        requestData.userId = AppConfig.getUserId();

        return AppRequest.build(requestData);
    }

    public static AppRequest<GameJoinRequestData> buildTable(String tableId) {
        GameJoinRequestData requestData = new GameJoinRequestData();
        requestData.tableId = tableId;

        return AppRequest.build(requestData);
    }
}
