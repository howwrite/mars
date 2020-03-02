package com.github.howwrite.mars.sdk.config;

import com.github.howwrite.mars.sdk.exception.MarsException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author howwrite
 * @Description: mars相关配置
 * @create 2019/12/15 16:13
 */
@ConfigurationProperties(prefix = "mars")
public class MarsProperties {

    @NestedConfigurationProperty
    private MarsWxProperties marsWxProperties;

    /**
     * 是否打印相关log
     */
    private Boolean enableLog = false;

    /**
     * 是否启用对 {@link MarsException}的拦截
     */
    private Boolean enableExceptionAdvice = true;

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

    public Boolean getEnableLog() {
        return enableLog;
    }

    public void setEnableLog(Boolean enableLog) {
        this.enableLog = enableLog;
    }

    public Boolean getEnableExceptionAdvice() {
        return enableExceptionAdvice;
    }

    public void setEnableExceptionAdvice(Boolean enableExceptionAdvice) {
        this.enableExceptionAdvice = enableExceptionAdvice;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
