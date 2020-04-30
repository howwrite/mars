package com.github.howwrite.mars.sdk.facade;

import java.util.Map;

/**
 * json处理器
 *
 * @author zhu.senlin
 * @date 2020/4/27 上午2:37:22
 */
public interface MarsJsonHandler {
    /**
     * 将字符串解析成类对象
     *
     * @param str   json字符串
     * @param clazz json串表示的类的类型对象
     * @param <T>   转换的类型
     * @return json解析的结果
     */
    <T> T parseObject(String str, Class<T> clazz);

    /**
     * 将json串转换为map类型对象,暂不支持有list类型值的对象
     *
     * @param string json字符串
     * @return map类型对象
     */
    Map<String, Object> parseMap(String string);

}
