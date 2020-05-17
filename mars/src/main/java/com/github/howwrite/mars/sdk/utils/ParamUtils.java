package com.github.howwrite.mars.sdk.utils;

import com.github.howwrite.mars.sdk.exception.MarsIllegalParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author howwrite
 * @date 2020/4/26 下午2:00:17
 */
public class ParamUtils {
    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new MarsIllegalParamException(message);
        }
    }

    public static void notEmpty(Object o, String message) {
        if (ObjectUtils.isEmpty(o)) {
            throw new MarsIllegalParamException(message);
        }
    }
}
