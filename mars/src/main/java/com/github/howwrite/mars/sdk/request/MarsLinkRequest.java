package com.github.howwrite.mars.sdk.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/8 上午8:39:41
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsLinkRequest extends BaseMarsRequest {
    private static final long serialVersionUID = 1622615335812840491L;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息描述
     */
    private String description;
    /**
     * 消息链接
     */
    private String url;
}
