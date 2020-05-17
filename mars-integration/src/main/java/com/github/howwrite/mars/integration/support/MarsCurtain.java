package com.github.howwrite.mars.integration.support;

import com.github.howwrite.mars.sdk.facade.MarsResponseFactory;
import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author howwrite
 * @date 2020/3/28 上午1:15:10
 */
public interface MarsCurtain {
    /**
     * 处理微信消息前方法
     *
     * @param request 请求内容
     * @param params  上下文传递的参数集合
     */
    default void before(BaseMarsRequest request, Map<Object, Object> params) {
    }

    /**
     * 处理异常时的方法
     *
     * @param request   消息内容
     * @param throwable 异常信息
     * @param params    上下文传递的参数集合
     * @return 回复的消息
     */
    default BaseMarsResponse error(BaseMarsRequest request, Throwable throwable, Map<Object, Object> params) {
        LoggerFactory.getLogger(this.getClass()).warn("mars catch error", throwable);
        return MarsResponseFactory.createTextResponse(request, "server error");
    }

    /**
     * 消息处理完成后执行方法
     *
     * @param request  请求内容
     * @param response 回复内容
     * @param params   上下文传递的参数集合
     */
    default void complete(BaseMarsRequest request, BaseMarsResponse response, Map<Object, Object> params) {
    }

    /**
     * 默认消息处理器
     *
     * @param request 消息内容
     * @return 回复的消息
     */
    @NotNull
    default BaseMarsResponse defaultHandler(BaseMarsRequest request) {
        return MarsResponseFactory.createTextResponse(request, "hello");
    }
}