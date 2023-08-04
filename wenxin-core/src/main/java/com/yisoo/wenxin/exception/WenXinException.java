package com.yisoo.wenxin.exception;

/**
 * TODO
 *
 * @author mmciel 761998179@qq.com
 * @version 1.0.0
 * @date 2023/8/4 13:03
 * @update none
 */
public class WenXinException extends RuntimeException{
    private String msg;

    public WenXinException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public WenXinException() {
        super("未知错误");
        this.msg = "未知错误";
    }
}
