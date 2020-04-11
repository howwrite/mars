package com.github.howwrite.mars.sdk.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/4 下午10:37:42
 */
@Getter
@Setter
@ToString(callSuper = true)
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
}
