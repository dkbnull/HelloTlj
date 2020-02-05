package cn.wbnull.hellotlj.model;

import lombok.Data;

/**
 * 后台响应信息
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
@Data
public class AppResponse<T> {

    private String code;
    private String message;
    private T data;

    public boolean isSuccess() {
        return "1000".equals(this.code);
    }
}
