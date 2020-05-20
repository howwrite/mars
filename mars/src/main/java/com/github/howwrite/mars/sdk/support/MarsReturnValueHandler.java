package com.github.howwrite.mars.sdk.support;

import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
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
@RequiredArgsConstructor
public class MarsReturnValueHandler implements HandlerMethodReturnValueHandler {
    private static final Logger log = LoggerFactory.getLogger(MarsReturnValueHandler.class);

    private final WxUtils wxUtils;

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return ClassUtils.isAssignable(BaseMarsResponse.class, returnType.getNestedParameterType());
    }

    @Override
    public void handleReturnValue(@NotNull Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        BaseMarsResponse baseMarsResponse = (BaseMarsResponse) returnValue;
        baseMarsResponse.checkParam();
        String createTime = String.valueOf(System.currentTimeMillis());
        String result = baseMarsResponse.convertXmlString(createTime);
        if (baseMarsResponse.getEncryption()) {
            result = wxUtils.encryptMsg(result, createTime);
        }
        mavContainer.setRequestHandled(true);

        Assert.notNull(response, "response can not be null");
        response.setContentType("text/xml;charset=UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(result);
        } catch (IOException e) {
            log.warn("result print error. ", e);
        }
    }
}
