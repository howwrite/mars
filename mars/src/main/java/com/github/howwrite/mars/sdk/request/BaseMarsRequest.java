package com.github.howwrite.mars.sdk.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 微信请求参数
 *
 * @author howwrite
 * @date 2020/1/1 下午10:32:26
 */
@Getter
@Setter
@ToString(callSuper = true)
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
}
