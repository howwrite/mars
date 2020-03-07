package com.github.howwrite.mars.sdk.request;

/**
 * @author howwrite
 * @date 2020/3/4 下午10:37:42
 */
public class MarsTextRequest extends BaseMarsRequest {
    /**
     * 文本消息内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
