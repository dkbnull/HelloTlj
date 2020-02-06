package cn.wbnull.hellotlj.service.user;

import cn.wbnull.hellotlj.model.AppRequest;
import cn.wbnull.hellotlj.model.AppResponse;
import cn.wbnull.hellotlj.model.user.RegisterRequestData;
import cn.wbnull.hellotlj.model.user.RegisterResponseData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 用户接口
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public interface IUserService {

    @POST("user/register")
    Call<AppResponse<RegisterResponseData>> register(@Body AppRequest<RegisterRequestData> request);

    @POST("user/login")
    Call<AppResponse<RegisterResponseData>> login(@Body AppRequest<RegisterRequestData> request);
}
