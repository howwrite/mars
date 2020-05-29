package com.github.howwrite.mars.sdk.config;

import com.github.howwrite.mars.sdk.facade.MarsJsonHandler;
import com.github.howwrite.mars.sdk.facade.impl.json.FastJsonHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author howwrite
 * @date 2020/5/20 下午10:40:21
 */
@ConditionalOnClass(name = "com.alibaba.fastjson.JSONObject")
public class MarsFastjsonConfiguration {
    @Bean
    @ConditionalOnMissingBean(MarsJsonHandler.class)
    public MarsJsonHandler marsJsonHandler() {
        return new FastJsonHandler();
    }
}
