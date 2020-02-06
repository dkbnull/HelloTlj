package cn.wbnull.hellotlj.util;

import java.util.concurrent.TimeUnit;

import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.constant.CommonConstants;
import cn.wbnull.hellotlj.model.AppResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * 网络请求工具类
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public class WebUtils {

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private static void init() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60L, TimeUnit.SECONDS)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .build();
        retrofit = (new Retrofit.Builder())
                .baseUrl(CommonConstants.URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(okHttpClient).build();
    }

    public static <T> T create(Class<T> clazz) {
        if (retrofit == null) {
            init();
        }

        return retrofit.create(clazz);
    }

    public static <T> void doPost(Call<AppResponse<T>> call, final GlobalCallback<T> callback) {
        call.enqueue(new Callback<AppResponse<T>>() {
            @Override
            public void onResponse(Call<AppResponse<T>> call, Response<AppResponse<T>> response) {
                if (response.code() != 200) {
                    callback.onFailure(String.format("响应码：%s，响应信息：%s", response.code(), response.message()));
                    return;
                }

                AppResponse<T> result = response.body();
                if (result == null || response.body() == null) {
                    callback.onFailure("返回参数为空");
                    return;
                }

                if (result.isSuccess()) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onFailure(result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AppResponse<T>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
