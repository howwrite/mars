package com.github.howwrite.mars.sdk.facade.impl.cache;

import com.github.howwrite.mars.sdk.facade.MarsCacheExtend;
import com.github.howwrite.mars.sdk.info.CacheInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author howwrite
 * @date 2020/4/30 下午1:43:32
 */
public class MarsRedisCache implements MarsCacheExtend {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveValue(String key, Object value, Integer expires) {
        redisTemplate.opsForValue().set(key, value, expires, TimeUnit.SECONDS);
    }

    @Override
    public CacheInfo getValue(String key) {
        Object value = redisTemplate.opsForValue().get(key);
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
