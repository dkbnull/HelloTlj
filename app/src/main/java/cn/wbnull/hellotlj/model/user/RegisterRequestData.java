package cn.wbnull.hellotlj.model.user;

import cn.wbnull.hellotlj.model.AppRequest;
import lombok.Data;

/**
 * 用户注册接口   前台请求信息
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class RegisterRequestData {

    private String nickname;
    private String username;
    private String password;

    public static AppRequest<RegisterRequestData> build(String nickname, String username, String password) {
        RegisterRequestData requestData = new RegisterRequestData();
        requestData.nickname = nickname;
        requestData.username = username;
        requestData.password = password;

        return AppRequest.build(requestData);
    }
}
