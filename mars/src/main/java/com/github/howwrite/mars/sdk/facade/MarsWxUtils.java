package com.github.howwrite.mars.sdk.facade;

import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.constants.MarsConstants;
import com.github.howwrite.mars.sdk.info.AccessTokenInfo;
import com.github.howwrite.mars.sdk.info.CacheInfo;
import com.github.howwrite.mars.sdk.info.TempResourceInfo;
import com.github.howwrite.mars.sdk.utils.MarsStringUtils;
import com.github.howwrite.mars.sdk.utils.ParamUtils;
import com.github.howwrite.mars.sdk.utils.http.HttpUtils;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信相关工具
 *
 * @author howwrite
 * @date 2020/4/16 下午7:33:12
 */
@Slf4j
public class MarsWxUtils {
    @Resource
    private HttpUtils httpUtils;
    @Resource
    private MarsWxProperties marsWxProperties;
    /**
     * json解析器接口，依赖jackSon
     * {@link com.github.howwrite.mars.sdk.facade.impl.json.JacksonJsonHandler}
     * 如果需要更换json框架，请参考 https://github.com/howwrite/mars/blob/develop/README/MarsJsonHandler.md
     */
    @Resource
    private MarsJsonHandler marsJsonHandler;
    private List<MarsCacheExtend> marsCacheExtends;

    @Autowired(required = false)
    public void setMarsCacheExtends(List<MarsCacheExtend> marsCacheExtends) {
        this.marsCacheExtends = marsCacheExtends;
    }

    /**
     * 获取 AccessToken 先从缓存中获取，如果没有的话就从微信端获取
     *
     * @return AccessToken
     */
    @NotNull
    public AccessTokenInfo getAccessToken() {
        if (!CollectionUtils.isEmpty(marsCacheExtends)) {
            for (MarsCacheExtend marsCacheExtend : marsCacheExtends) {
                CacheInfo cacheInfo = marsCacheExtend.getValue(MarsConstants.ACCESS_TOKEN_CACHE_KEY);
                if (cacheInfo.getSuccess()) {
                    return new AccessTokenInfo(cacheInfo.getValue().toString(), cacheInfo.getExpires());
                }
            }
        }
        return refreshAccessToken();
    }

    /**
     * 从微信端获取AccessToken
     *
     * @return AccessToken
     * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
     */
    @NotNull
    public AccessTokenInfo refreshAccessToken() {
        String appSecret = marsWxProperties.getAppSecret();
        ParamUtils.notBlank(appSecret, "place config AppSecret to 'mars.weixin.app-secret'");
        Map<String, Object> queryAccessTokenParams = new HashMap<>(8);
        queryAccessTokenParams.put("grant_type", "client_credential");
        queryAccessTokenParams.put("appid", marsWxProperties.getAppId());
        queryAccessTokenParams.put("secret", appSecret);
        AccessTokenInfo accessTokenInfo = httpUtils.get(MarsConstants.GET_ACCESS_TOKEN_URL, queryAccessTokenParams, response -> {
            try {
                String bodyString = response.body().string();
                Map<String, Object> stringObjectMap = marsJsonHandler.parseMap(bodyString);
                if (stringObjectMap.containsKey(MarsConstants.ACCESS_TOKEN_RESPONSE_TOKEN_KEY)) {
                    String accessToken = (String) stringObjectMap.get("access_token");
                    Integer expires = (Integer) stringObjectMap.get("expires_in");
                    return new AccessTokenInfo(accessToken, expires);
                }
                return AccessTokenInfo.fail("get access token fail", bodyString);
            } catch (IOException e) {
                log.warn("get response body fail", e);
                return AccessTokenInfo.fail(e, "get response body fail: ");
            }
        }, e -> {
            log.warn("request accessToken Error", e);
            return AccessTokenInfo.fail(e, "request accessToken Error");
        });
        if (!ObjectUtils.isEmpty(accessTokenInfo) && accessTokenInfo.getSuccess()) {
            if (!CollectionUtils.isEmpty(marsCacheExtends)) {
                for (MarsCacheExtend marsCacheExtend : marsCacheExtends) {
                    marsCacheExtend.saveValue(MarsConstants.ACCESS_TOKEN_CACHE_KEY, accessTokenInfo.getAccessToken(), accessTokenInfo.getExpires());
                }
            }
        }
        return accessTokenInfo;
    }

    /**
     * 获得临时资源
     *
     * @param mediaId 资源id
     * @return 资源详情
     */
    public TempResourceInfo getTempResource(String mediaId) {
        Map<String, Object> queryMap = new HashMap<>(8);
        AccessTokenInfo accessTokenInfo = getAccessToken();
        if (!accessTokenInfo.getSuccess()) {
            return TempResourceInfo.fail(accessTokenInfo.getThrowable(), accessTokenInfo.getErrorMsg());
        }
        queryMap.put("access_token", accessTokenInfo.getAccessToken());
        queryMap.put("media_id", mediaId);
        return httpUtils.get(MarsConstants.GET_TEMP_MEDIA_RES_URL, queryMap, response -> {
            try (InputStream inputStream = response.body().byteStream()) {
                if (!MarsConstants.TEMP_RESP_SUCCESS_TYPE.contains(response.body().contentType().type())) {
                    return TempResourceInfo.fail("request temp res error", response.body().string());
                }
                String contentDisposition = response.headers().get("Content-disposition");
                String filename = MarsStringUtils.getFileName(contentDisposition);
                byte[] bytes = ByteStreams.toByteArray(inputStream);
                return new TempResourceInfo(filename, bytes);
            } catch (IOException e) {
                return TempResourceInfo.fail(e, "get temp resource io error");
            }
        }, e -> TempResourceInfo.fail(e, "client temp res error"));
    }
}
