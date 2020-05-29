package com.github.howwrite.mars.sdk.config;

import com.github.howwrite.mars.sdk.facade.MarsJsonHandler;
import com.github.howwrite.mars.sdk.facade.impl.json.JacksonJsonHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author howwrite
 * @date 2020/5/20 下午10:38:19
 */
@ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
public class MarsJacksonConfiguration {
    @Bean
    @ConditionalOnMissingBean(MarsJsonHandler.class)
    public MarsJsonHandler marsJsonHandler() {
        return new JacksonJsonHandler();
    }
}
