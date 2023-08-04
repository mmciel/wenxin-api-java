package com.yisoo.wenxin.demo.config;

import com.yisoo.wenxin.WenXinClient;
import com.yisoo.wenxin.config.WenXinConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
@Slf4j
public class BaiduConfig {

    @Value("${baidu.wenxin.appName}")
    private String appName;
    @Value("${baidu.wenxin.appid}")
    private String appid;
    @Value("${baidu.wenxin.apiKey}")
    private String apiKey;
    @Value("${baidu.wenxin.secretKey}")
    private String secretKey;

    @Bean
    public WenXinClient wenXinClient() {
        String accessToken = WenXinConfig.getAccessToken(apiKey, secretKey);
        return WenXinClient.builder()
                .logLevel(HttpLoggingInterceptor.Level.BASIC)
                .accessToken(accessToken).build();
    }
}
