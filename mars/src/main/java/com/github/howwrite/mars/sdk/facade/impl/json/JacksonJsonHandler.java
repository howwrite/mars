package com.github.howwrite.mars.sdk.facade.impl.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.howwrite.mars.sdk.exception.MarsException;
import com.github.howwrite.mars.sdk.facade.MarsJsonHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhu.senlin
 * @date 2020/4/27 上午2:46:12
 */
@Component
@Slf4j
public class JacksonJsonHandler implements MarsJsonHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> T parseObject(String str, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            throw new MarsException("JSON string parsing failed", e, str, clazz);
        }
    }

    @Override
    public Map<String, Object> parseMap(String string) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(string);
            return parseMap(jsonNode);
        } catch (JsonProcessingException e) {
            log.warn("json parse map error, jsonString:" + string, e);
            return Collections.emptyMap();
        }
    }

    private Map<String, Object> parseMap(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            log.warn("current not support array type,json:{}", jsonNode.toString());
            return Collections.emptyMap();
        }
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        Map<String, Object> result = new HashMap<>();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> next = fields.next();
            String key = next.getKey();
            JsonNode value = next.getValue();
            if (!value.isValueNode()) {
                result.put(key, parseMap(value));
            } else if (value.isTextual()) {
                result.put(key, value.asText());
            } else if (value.isInt()) {
                result.put(key, value.asInt());
            }
        }
        return result;
    }
}
