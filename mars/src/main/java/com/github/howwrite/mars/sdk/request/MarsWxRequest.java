package com.github.howwrite.mars.sdk.request;

/**
 * 微信请求参数
 *
 * @author howwrite
 * @date 2020/1/1 下午10:32:26
 */
public class MarsWxRequest {
    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方微信号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息创建时间
     */
    private Long createTime;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 是否为加密消息
     */
    private Boolean encryption;

    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
