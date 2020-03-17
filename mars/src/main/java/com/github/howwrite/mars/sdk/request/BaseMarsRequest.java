package com.github.howwrite.mars.sdk.request;

import java.io.Serializable;

/**
 * 微信请求参数
 *
 * @author howwrite
 * @date 2020/1/1 下午10:32:26
 */
public abstract class BaseMarsRequest implements Serializable {
    private static final long serialVersionUID = 9143636257235861628L;
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
     * 消息id
     */
    private Long msgId;

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

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
