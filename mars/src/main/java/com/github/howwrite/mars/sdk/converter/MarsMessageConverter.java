package com.github.howwrite.mars.sdk.converter;

import com.github.howwrite.mars.sdk.response.BaseMarsWxResponse;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author howwrite
 * @date 2020/3/1 下午2:47:22
 */
@Component
public class MarsMessageConverter implements HttpMessageConverter<BaseMarsWxResponse> {

    @Resource
    private WxUtils wxUtils;

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return ClassUtils.isAssignable(BaseMarsWxResponse.class, clazz);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.ALL);
    }

    @Override
    public BaseMarsWxResponse read(Class<? extends BaseMarsWxResponse> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(BaseMarsWxResponse baseMarsWxResponse, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String createTime = String.valueOf(System.currentTimeMillis());
        String result = baseMarsWxResponse.convertXmlString(createTime);
        if (baseMarsWxResponse.getEncryption()) {
            result = wxUtils.encryptMsg(result, createTime);
        }
        StreamUtils.copy(result, StandardCharsets.UTF_8, outputMessage.getBody());
    }
}
