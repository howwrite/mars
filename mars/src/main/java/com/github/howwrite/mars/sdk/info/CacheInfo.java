package com.github.howwrite.mars.sdk.info;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 缓存信息
 *
 * @author howwrite
 * @date 2020/5/16 下午3:51:08
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CacheInfo extends BaseMarsInfo {
    private static final long serialVersionUID = 3558016049304803970L;
    private String value;
    private Integer expires;

    public CacheInfo(String value, Integer expires) {
        this.value = value;
        this.expires = expires;
    }

    public static CacheInfo empty(String key) {
        CacheInfo cacheInfo = new CacheInfo();
        cacheInfo.setSuccess(false);
        cacheInfo.setErrorMsg("can get any value, please check, key:" + key);
        return cacheInfo;
    }
}
