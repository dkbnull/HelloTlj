package cn.wbnull.hellotlj.service.game;

import cn.wbnull.hellotlj.model.AppRequest;
import cn.wbnull.hellotlj.model.AppResponse;
import cn.wbnull.hellotlj.model.game.GameJoinRequestData;
import cn.wbnull.hellotlj.model.game.GameJoinResponseData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 游戏接口
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public interface IGameService {

    @POST("game/join")
    Call<AppResponse<GameJoinResponseData>> join(@Body AppRequest<GameJoinRequestData> request);

    @POST("game/leave")
    Call<AppResponse<GameJoinResponseData>> leave(@Body AppRequest<GameJoinRequestData> request);

    @POST("game/info")
    Call<AppResponse<GameJoinResponseData>> info(@Body AppRequest<GameJoinRequestData> request);
}
