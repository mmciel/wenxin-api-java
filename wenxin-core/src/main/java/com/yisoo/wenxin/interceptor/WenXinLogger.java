package com.yisoo.wenxin.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * TODO
 *
 * @author mmciel 761998179@qq.com
 * @version 1.0.0
 * @date 2023/8/3 14:17
 * @update none
 */
@Slf4j
public class WenXinLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        log.info("文心一言>>>:{}", message);
    }
}
