package com.yisoo.wenxin.sse;

import cn.hutool.json.JSONUtil;
import com.yisoo.wenxin.entity.ChatResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.Objects;

/**
 * TODO
 *
 * @author mmciel 761998179@qq.com
 * @version 1.0.0
 * @date 2023/8/4 13:03
 * @update none
 */
@Slf4j
public class ConsoleExampleEventSourceListener extends EventSourceListener {

    @Override
    public void onOpen(EventSource eventSource, Response response) {
        log.info("建立sse连接...");
    }

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        ChatResponse bean = JSONUtil.parseObj(data).toBean(ChatResponse.class);
        log.info("返回数据：{}", data);
        if (bean.getIs_end()) {
            log.info("返回数据结束了");
        }
    }

    @Override
    public void onClosed(EventSource eventSource) {
        log.info("关闭sse连接...");
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if(Objects.isNull(response)){
            log.error("sse连接异常:{}", t);
            eventSource.cancel();
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            // 错误处理 {"error_code":110,"error_msg":"Access token invalid or no longer valid"}，异常：{}
            log.error("sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("sse连接异常data：{}，异常：{}", response, t);
        }
        eventSource.cancel();
    }
}