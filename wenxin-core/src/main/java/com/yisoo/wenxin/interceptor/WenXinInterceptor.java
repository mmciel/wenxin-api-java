package com.yisoo.wenxin.interceptor;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * TODO
 *
 * @author mmciel 761998179@qq.com
 * @version 1.0.0
 * @date 2023/8/3 14:17
 * @update none
 */
public class WenXinInterceptor implements Interceptor {

    public WenXinInterceptor() {
    }
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Request request = original.newBuilder()
                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
