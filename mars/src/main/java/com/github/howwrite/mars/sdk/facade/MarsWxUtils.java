package com.github.howwrite.mars.sdk.facade;

import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.constants.MarsConstants;
import com.github.howwrite.mars.sdk.info.AccessTokenInfo;
import com.github.howwrite.mars.sdk.info.CacheInfo;
import com.github.howwrite.mars.sdk.info.TempResourceInfo;
import com.github.howwrite.mars.sdk.info.WeChatServerIpListInfo;
import com.github.howwrite.mars.sdk.utils.MarsStringUtils;
import com.github.howwrite.mars.sdk.utils.ParamUtils;
import com.github.howwrite.mars.sdk.utils.http.HttpUtils;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
@Component
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
                    return new AccessTokenInfo(cacheInfo.getValue(), cacheInfo.getExpires());
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
                if (ObjectUtils.isEmpty(response.body())) {
                    return AccessTokenInfo.fail("get access token response is empty, url:" + MarsConstants.GET_ACCESS_TOKEN_URL);
                }
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
            if (ObjectUtils.isEmpty(response.body())) {
                return TempResourceInfo.fail("get temp resource response body is empty,url:" + MarsConstants.GET_TEMP_MEDIA_RES_URL);
            }
            try (InputStream inputStream = response.body().byteStream()) {
                MediaType mediaType = response.body().contentType();
                if (ObjectUtils.isEmpty(mediaType) || !MarsConstants.TEMP_RESP_SUCCESS_TYPE.contains(mediaType.type())) {
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

    /**
     * 获取微信callback IP地址
     * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_the_WeChat_server_IP_address.html
     *
     * @return ip地址列表
     */
    public WeChatServerIpListInfo getCallBackIpList() {
        return getIpList(MarsConstants.GET_CALLBACK_IP_URL, "get call back ip list");
    }

    /**
     * 获取微信API接口 IP地址
     * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_the_WeChat_server_IP_address.html
     *
     * @return ip地址列表
     */
    public WeChatServerIpListInfo getApiDomainIpList() {
        return getIpList(MarsConstants.GET_API_DOMAIN_IP_LIST, "get api domain ip list");
    }

    /**
     * 获取微信服务器ip地址列表
     * {@link #getCallBackIpList()}
     * {@link #getApiDomainIpList()}
     *
     * @param url 请求的接口地址
     * @return 接口地址列表
     */
    private WeChatServerIpListInfo getIpList(String url, String serverName) {
        Map<String, Object> queryMap = new HashMap<>(8);
        AccessTokenInfo accessTokenInfo = getAccessToken();
        if (!accessTokenInfo.getSuccess()) {
            return WeChatServerIpListInfo.fail(accessTokenInfo.getThrowable(), accessTokenInfo.getErrorMsg());
        }
        queryMap.put("access_token", accessTokenInfo.getAccessToken());
        return httpUtils.get(url, queryMap, response -> {
            try {
                if (ObjectUtils.isEmpty(response.body())) {
                    return WeChatServerIpListInfo.fail(serverName + " response body is empty url:" + url);
                }
                String responseString = response.body().string();
                Map<String, Object> responseMap = marsJsonHandler.parseMap(responseString);
                Object ipList = responseMap.get("ip_list");
                if (ObjectUtils.isEmpty(ipList) || !(ipList instanceof List<?>)) {
                    return WeChatServerIpListInfo.fail(serverName + " server error", responseString);
                }
                List<String> ipListString = new ArrayList<>(32);
                for (Object o : (List<?>) ipList) {
                    ipListString.add(o.toString());
                }
                return new WeChatServerIpListInfo(ipListString);
            } catch (IOException e) {
                return WeChatServerIpListInfo.fail(e, serverName + " io error");
            }
        }, e -> WeChatServerIpListInfo.fail(e, serverName + " client error"));
    }
}