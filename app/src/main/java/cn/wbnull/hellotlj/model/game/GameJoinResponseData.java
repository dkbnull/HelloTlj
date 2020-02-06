package cn.wbnull.hellotlj.model.game;

import java.util.List;

import cn.wbnull.hellotlj.model.user.RegisterResponseData;
import lombok.Data;

/**
 * 加入游戏接口   后台返回信息
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class GameJoinResponseData {

    private Gametable gametable;
    private List<RegisterResponseData> userinfos;
}
