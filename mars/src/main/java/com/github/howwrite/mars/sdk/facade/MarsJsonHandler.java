package com.github.howwrite.mars.sdk.facade;

import com.github.howwrite.mars.sdk.exception.MarsJsonException;

import java.util.Map;

/**
 * json处理器
 *
 * @author howwrite
 * @date 2020/4/27 上午2:37:22
 */
public interface MarsJsonHandler {
    /**
     * 将json串转换为map类型对象
     *
     * @param string json字符串
     * @return map类型对象
     */
    Map<String, Object> parseMap(String string);

    /**
     * 将对象转换成json字符串
     *
     * @param object 要转换的对象
     * @return json字符串
     * @throws MarsJsonException json转换异常
     */
    String toJsonString(Object object) throws MarsJsonException;
}
