package com.github.howwrite.mars.sdk.facade.impl.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.howwrite.mars.sdk.exception.MarsJsonException;
import com.github.howwrite.mars.sdk.facade.MarsJsonHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author howwrite
 * @date 2020/5/17 下午3:06:06
 */
public class FastJsonHandler implements MarsJsonHandler {
    @Override
    public Map<String, Object> parseMap(String string) {
        JSONObject jsonObject = JSONObject.parseObject(string);
        return getJsonMap(jsonObject);
    }

    private Map<String, Object> getJsonMap(JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>(16);
        Map<String, Object> innerMap = jsonObject.getInnerMap();
        innerMap.forEach((key, value) -> {
            if (value instanceof JSONArray) {
                map.put(key, ((JSONArray) value).toJavaList(Object.class));
            } else if (value instanceof JSONObject) {
                map.put(key, getJsonMap((JSONObject) value));
            } else {
                map.put(key, value);
            }
        });
        return map;
    }

    @Override
    public String toJsonString(Object object) throws MarsJsonException {
        try {
            return JSONObject.toJSONString(object);
        } catch (Exception e) {
            throw new MarsJsonException(e);
        }
    }
}