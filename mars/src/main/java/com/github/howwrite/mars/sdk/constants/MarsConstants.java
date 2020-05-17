package com.github.howwrite.mars.sdk.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author howwrite
 * @date 2019/12/22 下午10:41:55
 */
public class MarsConstants {
    public static final String MARS_WX_CHECK_REQUEST_METHOD = "GET";
    public static final String MARS_WX_HANDLE_MESSAGE_METHOD = "POST";
    public static final String MARS_WX_SIGNATURE_PARAM_NAME = "signature";
    public static final String MARS_WX_ECHOSTR_PARAM_NAME = "echostr";
    public static final String MARS_WX_TIMESTAMP_PARAM_NAME = "timestamp";
    public static final String MARS_WX_NONCE_PARAM_NAME = "nonce";

    public static final String MARS_WX_MSG_TYPE_NAME = "MsgType";

    /**
     * 获取accessToken的地址
     */
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 获取临时媒体资源的地址
     */
    public static final String GET_TEMP_MEDIA_RES_URL = "https://api.weixin.qq.com/cgi-bin/media/get";

    public static final String GET_ACCESS_TOKEN_DEVELOP_URL = "https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html";
    public static final String GET_TEMP_RESOURCES_DEVELOP_URL = "https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Get_temporary_materials.html";

    /**
     * 获取AccessToken响应中的AccessToken的key
     */
    public static final String ACCESS_TOKEN_RESPONSE_TOKEN_KEY = "access_token";
    /**
     * 获取临时资源正确的响应类型
     */
    public static final List<String> TEMP_RESP_SUCCESS_TYPE = Arrays.asList("audio", "image");

    public static final String ACCESS_TOKEN_CACHE_KEY = "mars_access_token_cache_key";
}
