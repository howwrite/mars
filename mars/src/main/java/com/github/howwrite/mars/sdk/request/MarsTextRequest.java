package com.github.howwrite.mars.sdk.request;

/**
 * @author howwrite
 * @date 2020/3/4 下午10:37:42
 */
public class MarsTextRequest extends BaseMarsRequest {
    private static final long serialVersionUID = 6475259901584048950L;
    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 点击的菜单ID
     */
    private Long bizMsgMenuId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBizMsgMenuId() {
        return bizMsgMenuId;
    }

    public void setBizMsgMenuId(Long bizMsgMenuId) {
        this.bizMsgMenuId = bizMsgMenuId;
    }
}
