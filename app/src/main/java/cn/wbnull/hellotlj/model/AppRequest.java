package cn.wbnull.hellotlj.model;

import cn.wbnull.helloutil.util.DateUtils;
import cn.wbnull.helloutil.util.JSONUtils;
import lombok.Data;

/**
 * 请求后台信息
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class AppRequest<T> {

    /**
     * 业务数据
     */
    private T data;

    /**
     * 签名
     */
    private String sign;

    /**
     * 时间戳
     */
    private String timestamp;

    public static <T> AppRequest<T> build(T t) {
        AppRequest request = new AppRequest();
        request.data = t;
        request.timestamp = DateUtils.currentTime();
        request.sign = "";

        return request;
    }

    @Override
    public String toString() {
        return JSONUtils.javaBeanToJSON(this).toString();
    }
}
