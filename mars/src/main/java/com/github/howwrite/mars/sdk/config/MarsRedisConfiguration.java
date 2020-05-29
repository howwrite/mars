package com.github.howwrite.mars.sdk.config;

import com.github.howwrite.mars.sdk.facade.MarsCacheExtend;
import com.github.howwrite.mars.sdk.facade.impl.cache.MarsRedisCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author howwrite
 * @date 2020/5/20 下午10:15:35
 */
@ConditionalOnClass(StringRedisTemplate.class)
public class MarsRedisConfiguration {
    @Bean
    @ConditionalOnMissingBean(MarsCacheExtend.class)
    public MarsCacheExtend marsCacheExtend(StringRedisTemplate redisTemplate) {
        return new MarsRedisCache(redisTemplate);
    }
}
