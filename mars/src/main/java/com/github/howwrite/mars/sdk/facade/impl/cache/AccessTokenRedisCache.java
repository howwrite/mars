package com.github.howwrite.mars.sdk.facade.impl.cache;

import com.github.howwrite.mars.sdk.facade.AccessTokenCacheExtend;
import com.github.howwrite.mars.sdk.info.AccessTokenInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author zhu.senlin
 * @date 2020/4/30 下午1:43:32
 */
@RequiredArgsConstructor
public class AccessTokenRedisCache implements AccessTokenCacheExtend {
    private static final String ACCESS_TOKEN_REDIS_KEY = "mars_accessToken_key";
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveAccessToken(String accessToken, Integer expires) {
        stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_REDIS_KEY, accessToken, expires, TimeUnit.SECONDS);
    }

    @Override
    public AccessTokenInfo getAccessToken() {
        String accessToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_REDIS_KEY);
        if (StringUtils.isEmpty(accessToken)) {
            return null;
        }
        Long expire = stringRedisTemplate.getExpire(ACCESS_TOKEN_REDIS_KEY, TimeUnit.SECONDS);
        int expires = 0;
        if (expire != null) {
            expires = expire.intValue();
        }
        return new AccessTokenInfo(accessToken, expires);
    }
}
