package com.github.howwrite.mars.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author howwrite
 * @Description 关于微信的配置
 * @create 2019/12/15 15:27
 */
@Data
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
    /**
     * 开发者密码
     */
    private String appSecret;

    /**
     * 获取AccessToken的重试次数
     */
    private Integer getAccessTokenRetry = 3;
}
