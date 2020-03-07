package com.github.howwrite.mars.sdk.support;

import com.github.howwrite.mars.sdk.constants.MarsConstants;
import com.github.howwrite.mars.sdk.enums.MarsRequestTypeEnum;
import com.github.howwrite.mars.sdk.exception.MarsErrorCode;
import com.github.howwrite.mars.sdk.exception.MarsException;
import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author howwrite
 * @date 2020/3/4 下午7:37:55
 */
public class MarsResolver implements HandlerMethodArgumentResolver {
    private static final Logger log = LoggerFactory.getLogger(MarsResolver.class);
    private static final Map<Class<? extends BaseMarsRequest>, List<Method>> CLASS_METHOD_MAP = new HashMap<>();
    private final WxUtils wxUtils;


    public MarsResolver(WxUtils wxUtils) {
        this.wxUtils = wxUtils;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ClassUtils.isAssignable(BaseMarsRequest.class, parameter.getNestedParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.notNull(request, "request get error");
        String signature = request.getParameter(MarsConstants.MARS_WX_SIGNATURE_PARAM_NAME);
        String echostr = request.getParameter(MarsConstants.MARS_WX_ECHOSTR_PARAM_NAME);
        String timestamp = request.getParameter(MarsConstants.MARS_WX_TIMESTAMP_PARAM_NAME);
        String nonce = request.getParameter(MarsConstants.MARS_WX_NONCE_PARAM_NAME);
        log.debug("decrypt message.signature:{}, echostr:{}, timestamp:{}, nonce:{}", signature, echostr, timestamp, nonce);

        ServletInputStream inputStream = request.getInputStream();
        Map<String, Object> map = wxUtils.parseXml(inputStream, signature, timestamp, nonce);
        String type = (String) map.get("MsgType");
        Class<? extends BaseMarsRequest> requestClazz = MarsRequestTypeEnum.getRequestClazz(type);
        if (ObjectUtils.isEmpty(requestClazz)) {
            log.warn("This type of request is not currently supported,type:{}", type);
            throw new MarsException(MarsErrorCode.UNABLE_TO_PROCESS_THIS_REQUEST);
        }
        BaseMarsRequest baseMarsRequest = requestClazz.newInstance();
        List<Method> methods = getAllSetMethod(baseMarsRequest.getClass());
        for (Method method : methods) {
            if (!method.getName().startsWith("set")) {
                continue;
            }
            String name = method.getName().substring(3);
            Object value = map.get(name);
            if (!ObjectUtils.isEmpty(value) && value.getClass().equals(method.getParameterTypes()[0])) {
                method.invoke(baseMarsRequest, value);
            }
        }
        return baseMarsRequest;
    }

    private List<Method> getAllSetMethod(Class<? extends BaseMarsRequest> clazz) {
        List<Method> methods = CLASS_METHOD_MAP.get(clazz);
        if (!CollectionUtils.isEmpty(methods)) {
            return methods;
        }
        Method[] allMethod = clazz.getMethods();
        List<Method> allSetMethod = new ArrayList<>();
        for (Method method : allMethod) {
            if (method.getParameterCount() == 1 && method.getName().startsWith("set")) {
                allSetMethod.add(method);
            }
        }
        CLASS_METHOD_MAP.put(clazz, allSetMethod);
        return allSetMethod;
    }
}
