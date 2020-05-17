package com.github.howwrite.mars.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author howwrite
 * @Description mars相关配置
 * @date 2019/12/15 16:13
 */
@Data
@ConfigurationProperties(prefix = "mars")
public class MarsProperties {

    @NestedConfigurationProperty
    private MarsWxProperties marsWxProperties;

    /**
     * 处理微信请求的路径
     */
    private String path;

    public MarsWxProperties getMarsWxProperties() {
        return marsWxProperties;
    }

    public void setMarsWxProperties(MarsWxProperties marsWxProperties) {
        this.marsWxProperties = marsWxProperties;
    }
}
