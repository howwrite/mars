package com.github.howwrite.mars.integration.support;

import com.github.howwrite.mars.sdk.request.*;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;

/**
 * @author howwrite
 * @date 2020/3/22 下午1:18:41
 */
public interface MarsMessageHandler {
    /**
     * 处理文本消息方法
     *
     * @param request 文本消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doText(MarsTextRequest request) throws Throwable {
        return null;
    }

    /**
     * 处理事件消息方法
     *
     * @param request 事件消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doEvent(MarsEventRequest request) throws Throwable {
        return null;
    }

    /**
     * 处理图片消息方法
     *
     * @param request 图片消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doImage(MarsImageRequest request) throws Throwable {
        return null;
    }

    /**
     * 处理链接消息方法
     *
     * @param request 链接消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doLink(MarsLinkRequest request) throws Throwable {
        return null;
    }

    /**
     * 处理位置消息方法
     *
     * @param request 位置消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doLocation(MarsLocationRequest request) throws Throwable {
        return null;
    }

    /**
     * 处理短视频消息方法
     *
     * @param request 短视频消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doShortVideo(MarsShortVideoRequest request) throws Throwable {
        return null;
    }

    /**
     * 处理视频消息方法
     *
     * @param request 视频消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doVideo(MarsVideoRequest request) throws Throwable {
        return null;
    }

    /**
     * 处理语音消息方法
     *
     * @param request 语音消息内容
     * @return 回复的消息
     * @throws Throwable 异常
     */
    default BaseMarsResponse doVoice(MarsVoiceRequest request) throws Throwable {
        return null;
    }
}