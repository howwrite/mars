package com.github.howwrite.mars.sdk.facade;

import com.github.howwrite.mars.sdk.info.CacheInfo;

import javax.validation.constraints.NotNull;

/**
 * mars缓存接口
 *
 * @author howwrite
 * @date 2020/4/16 下午7:48:11
 */
public interface MarsCacheExtend {
    /**
     * 缓存值
     *
     * @param key     要保存的key
     * @param value   要保存的值
     * @param expires token有效时间，单位: 秒
     */
    void saveValue(String key, String value, Integer expires);

    /**
     * 获取缓存值
     *
     * @param key 缓存的key
     * @return 缓存的值
     */
    @NotNull
    CacheInfo getValue(String key);
}
