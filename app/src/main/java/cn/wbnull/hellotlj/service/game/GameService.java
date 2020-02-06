package cn.wbnull.hellotlj.service.game;

import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.model.AppRequest;
import cn.wbnull.hellotlj.model.AppResponse;
import cn.wbnull.hellotlj.model.game.GameJoinRequestData;
import cn.wbnull.hellotlj.model.game.GameJoinResponseData;
import cn.wbnull.hellotlj.util.WebUtils;
import retrofit2.Call;

/**
 * 游戏接口服务
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public class GameService {

    public static final String GAME_INFO = "/game/info";

    public static void join(final String tableId, final GlobalCallback<GameJoinResponseData> callback) {
        AppRequest<GameJoinRequestData> requestData = GameJoinRequestData.build(tableId);
        Call<AppResponse<GameJoinResponseData>> call = WebUtils.create(IGameService.class).join(requestData);
        WebUtils.doPost(call, callback);
    }

    public static void info(final String tableId, final GlobalCallback<GameJoinResponseData> callback) {
        AppRequest<GameJoinRequestData> requestData = GameJoinRequestData.build(tableId);
        Call<AppResponse<GameJoinResponseData>> call = WebUtils.create(IGameService.class).info(requestData);
        WebUtils.doPost(call, callback);
    }
}
