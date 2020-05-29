package com.github.howwrite.mars.sdk.facade.impl.cache;

import com.github.howwrite.mars.sdk.facade.MarsCacheExtend;
import com.github.howwrite.mars.sdk.info.CacheInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author howwrite
 * @date 2020/4/30 下午1:43:32
 */
@RequiredArgsConstructor
public class MarsRedisCache implements MarsCacheExtend {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void saveValue(String key, String value, Integer expires) {
        redisTemplate.opsForValue().set(key, value, expires, TimeUnit.SECONDS);
    }

    @Override
    public CacheInfo getValue(String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (ObjectUtils.isEmpty(value)) {
            return CacheInfo.empty(key);
        }
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        int expires = 0;
        if (expire != null) {
            expires = expire.intValue();
        }
        return new CacheInfo(value, expires);
    }
}
