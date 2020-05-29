package com.github.howwrite.mars.sdk.config;

import com.github.howwrite.mars.sdk.facade.MarsCacheExtend;
import com.github.howwrite.mars.sdk.facade.impl.cache.MarsCacheExtendImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author howwrite
 * @date 2020/5/20 下午10:17:10
 */
@AutoConfigureAfter(MarsRedisConfiguration.class)
public class MarsDefaultConfiguration {
    @Bean
    @ConditionalOnMissingBean(MarsCacheExtend.class)
    public MarsCacheExtend marsCacheExtend() {
        return new MarsCacheExtendImpl();
    }
}
