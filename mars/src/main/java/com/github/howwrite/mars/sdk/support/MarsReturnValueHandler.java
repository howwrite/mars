package com.github.howwrite.mars.sdk.support;

import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author howwrite
 * @date 2020/3/6 上午11:35:36
 */
public class MarsReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final WxUtils wxUtils;

    public MarsReturnValueHandler(WxUtils wxUtils) {
        this.wxUtils = wxUtils;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return ClassUtils.isAssignable(BaseMarsResponse.class, returnType.getNestedParameterType());
    }

    @Override
    public void handleReturnValue(@NotNull Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        BaseMarsResponse baseMarsResponse = (BaseMarsResponse) returnValue;
        String createTime = String.valueOf(System.currentTimeMillis());
        String result = baseMarsResponse.convertXmlString(createTime);
        if (baseMarsResponse.getEncryption()) {
            result = wxUtils.encryptMsg(result, createTime);
        }
        mavContainer.setRequestHandled(true);

        Assert.notNull(response, "response can not be null");
        response.setContentType(MediaType.APPLICATION_XML.getType());

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.print(result);
            writer.close();
        }
    }
}