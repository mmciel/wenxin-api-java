package com.yisoo.wenxin.constant;


/**
 * https://cloud.baidu.com/doc/WENXINWORKSHOP/s/Nlks5zkzu
 */
public enum ModelE {
    ERNIE_Bot("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions","ERNIE_Bot"),
    ERNIE_Bot_turbo("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant", "ERNIE_Bot_turbo");
    private final String apiHost;
    private final String label;
    ModelE(String code, String label) {
        this.apiHost = code;
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

    public String getApiHost() {
        return apiHost;
    }

}
