package cn.wbnull.hellotlj.boot;

/**
 * 接口服务访问回调
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public interface GlobalCallback<T> {

    void onSuccess(T t);

    void onFailure(String msg);
}
