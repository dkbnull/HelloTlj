package cn.wbnull.hellotlj.service;

import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.model.AppRequest;
import cn.wbnull.hellotlj.model.AppResponse;
import cn.wbnull.hellotlj.model.user.RegisterRequestData;
import cn.wbnull.hellotlj.model.user.RegisterResponseData;
import retrofit2.Call;

/**
 * 用户接口服务
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public class UserService {

    public static void register(final String nickname, final String username, final String password,
                                final GlobalCallback<RegisterResponseData> callback) {
        AppRequest<RegisterRequestData> requestData = RegisterRequestData.build(nickname, username, password);
        Call<AppResponse<RegisterResponseData>> call = WebService.create(IUserService.class).register(requestData);
        WebService.doPost(call, callback);
    }

    public static void login(final String username, final String password,
                             final GlobalCallback<RegisterResponseData> callback) {
        AppRequest<RegisterRequestData> requestData = RegisterRequestData.build(null, username, password);
        Call<AppResponse<RegisterResponseData>> call = WebService.create(IUserService.class).login(requestData);
        WebService.doPost(call, callback);
    }
}
