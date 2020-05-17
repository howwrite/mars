package com.github.howwrite.mars.sdk.facade.impl.cache;

import com.github.howwrite.mars.sdk.facade.MarsCacheExtend;
import com.github.howwrite.mars.sdk.info.CacheInfo;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AccessToken本地缓存接口
 *
 * @author howwrite
 * @date 2020/5/5 下午3:57:47
 */
public class MarsCacheExtendImpl implements MarsCacheExtend {
    private final Map<String, Object> objectMap = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> expireTimeMap = new ConcurrentHashMap<>();

    @Override
    public void saveValue(String key, Object value, Integer expires) {
        objectMap.put(key, value);
        expireTimeMap.put(key, LocalDateTime.now().plusSeconds(expires.longValue()));
    }

    @Override
    public @NotNull CacheInfo getValue(String key) {
        Object o = objectMap.get(key);
        if (ObjectUtils.isEmpty(o)) {
            return CacheInfo.empty(key);
        }
        LocalDateTime localDateTime = expireTimeMap.get(key);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, localDateTime);
        long seconds = duration.getSeconds();
        if (seconds > 0) {
            return new CacheInfo(o, (int) seconds);
        }
        return CacheInfo.empty(key);
    }
}