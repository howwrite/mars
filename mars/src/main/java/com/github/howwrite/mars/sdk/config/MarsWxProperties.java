package com.github.howwrite.mars.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author howwrite
 * @Description: 关于微信的配置
 * @create 2019/12/15 15:27
 */
@ConfigurationProperties(prefix = "mars.weixin")
public class MarsWxProperties {

    /**
     * 微信令牌
     */
    private String token;
    /**
     * 开发者ID
     */
    private String appId;
    /**
     * 消息加解密密钥
     */
    private String encodingAesKey;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }
}
