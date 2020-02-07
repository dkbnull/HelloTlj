package cn.wbnull.hellotlj.model.user;

import lombok.Data;

/**
 * 用户注册接口   后台响应信息
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class RegisterResponseData {

    private String userId;
    private String nickname;
    private Integer score;
    private Integer winNum;
    private Integer failNum;
    private Integer dogfallNum;
}
