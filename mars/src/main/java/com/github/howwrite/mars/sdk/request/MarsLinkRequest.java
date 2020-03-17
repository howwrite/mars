package com.github.howwrite.mars.sdk.request;

/**
 * @author howwrite
 * @date 2020/3/8 上午8:39:41
 */
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
