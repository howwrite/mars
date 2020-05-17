package com.github.howwrite.mars.sdk.info;

import com.github.howwrite.mars.sdk.constants.MarsConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author howwrite
 * @date 2020/4/28 下午3:32:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccessTokenInfo extends BaseMarsInfo {
    private static final long serialVersionUID = 1364093383359130902L;
    private String accessToken;
    private Integer expires;

    public AccessTokenInfo() {
    }

    public AccessTokenInfo(String accessToken, Integer expires) {
        this.accessToken = accessToken;
        this.expires = expires;
    }

    public static AccessTokenInfo fail(String... errorMsgs) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        fullFailParams(accessTokenInfo, "please see " + MarsConstants.GET_ACCESS_TOKEN_DEVELOP_URL, errorMsgs);
        return accessTokenInfo;
    }

    public static AccessTokenInfo fail(Throwable throwable, String... errorMsgs) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        fullFailParams(accessTokenInfo, throwable, "please see " + MarsConstants.GET_ACCESS_TOKEN_DEVELOP_URL, errorMsgs);
        return accessTokenInfo;
    }
}
