package com.github.howwrite.mars.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author howwrite
 * @Description mars相关配置
 * @date 2019/12/15 16:13
 */
@ConfigurationProperties(prefix = "mars")
public class MarsProperties {

    @NestedConfigurationProperty
    private MarsWxProperties marsWxProperties;

    /**
     * 处理微信请求的路径
     */
    private String path = "/mars";

    public MarsWxProperties getMarsWxProperties() {
        return marsWxProperties;
    }

    public void setMarsWxProperties(MarsWxProperties marsWxProperties) {
        this.marsWxProperties = marsWxProperties;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
