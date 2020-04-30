package com.github.howwrite.mars.sdk.facade;

import com.github.howwrite.mars.sdk.info.AccessTokenInfo;

/**
 * AccessToken缓存接口
 *
 * @author zhu.senlin
 * @date 2020/4/16 下午7:48:11
 */
public interface AccessTokenCacheExtend {
    /**
     * 保存 AccessToken
     *
     * @param accessToken 要保存AccessToken
     * @param expires     token有效时间，单位: 秒
     */
    void saveAccessToken(String accessToken, Integer expires);

    /**
     * 获取AccessToken
     *
     * @return AccessToken值
     */
    AccessTokenInfo getAccessToken();
}
