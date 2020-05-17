package com.github.howwrite.mars.sdk.facade;

import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.response.*;
import com.github.howwrite.mars.sdk.response.param.NewsResponseParam;

import java.util.List;

/**
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Passive_user_reply_message.html
 *
 * @author howwrite
 * @date 2020/3/28 上午1:21:06
 */
public class MarsResponseFactory {

    /**
     * 生成图文类型响应
     *
     * @param request 请求
     * @param params  图文参数列表
     * @return 图文类型的响应
     */
    public static BaseMarsResponse createNewsResponse(BaseMarsRequest request, List<NewsResponseParam> params) {
        return createNewsResponse(request, params, request.getFromUserName());
    }

    /**
     * 生成图文类型响应
     *
     * @param request    请求
     * @param articles   图文参数列表
     * @param toUserName 要返回的用户openId
     * @return 图文类型的响应
     */
    public static BaseMarsResponse createNewsResponse(BaseMarsRequest request, List<NewsResponseParam> articles, String toUserName) {
        MarsNewsResponse response = new MarsNewsResponse();
        createResponse(request, response, toUserName);
        response.setArticles(articles);
        return response;
    }

    /**
     * 生成音乐类型响应
     *
     * @param request      用户请求
     * @param title        音乐标题
     * @param description  音乐描述
     * @param musicUrl     音乐链接
     * @param hqMusicUrl   高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @return 音乐类型的响应
     */
    public static BaseMarsResponse createMusicResponse(BaseMarsRequest request, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) {
        return createMusicResponse(request, title, description, musicUrl, hqMusicUrl, thumbMediaId, request.getFromUserName());
    }


    /**
     * 生成音乐类型响应
     *
     * @param request      用户请求
     * @param title        音乐标题
     * @param description  音乐描述
     * @param musicUrl     音乐链接
     * @param hqMusicUrl   高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @param toUserName   要返回的用户openId
     * @return 音乐类型的响应
     */
    public static BaseMarsResponse createMusicResponse(BaseMarsRequest request, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId, String toUserName) {
        MarsMusicResponse response = new MarsMusicResponse();
        createResponse(request, response, toUserName);
        response.setTitle(title);
        response.setDescription(description);
        response.setHqMusicUrl(hqMusicUrl);
        response.setMusicUrl(musicUrl);
        response.setThumbMediaId(thumbMediaId);
        return response;
    }

    /**
     * 创建视频类型的响应
     *
     * @param request     用户请求
     * @param mediaId     通过素材管理中的接口上传多媒体文件，得到的id
     * @param title       视频消息的标题
     * @param description 视频消息的描述
     * @return 视频类型的响应
     */
    public static BaseMarsResponse createVideoResponse(BaseMarsRequest request, String mediaId, String title, String description) {
        return createVideoResponse(request, mediaId, title, description, request.getFromUserName());
    }

    /**
     * 创建视频类型的响应
     *
     * @param request     用户请求
     * @param mediaId     通过素材管理中的接口上传多媒体文件，得到的id
     * @param title       视频消息的标题
     * @param description 视频消息的描述
     * @param toUserName  要返回的用户openId
     * @return 视频类型的响应
     */
    public static BaseMarsResponse createVideoResponse(BaseMarsRequest request, String mediaId, String title, String description, String toUserName) {
        MarsVideoResponse response = new MarsVideoResponse();
        createResponse(request, response, toUserName);
        response.setMediaId(mediaId);
        response.setTitle(title);
        response.setDescription(description);
        return response;
    }

    /**
     * 创建语音消息的响应
     *
     * @param request 用户请求
     * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
     * @return 语音类型的响应
     */
    public static BaseMarsResponse createVoiceResponse(BaseMarsRequest request, String mediaId) {
        return createVoiceResponse(request, mediaId, request.getFromUserName());
    }

    /**
     * 创建语音消息的响应
     *
     * @param request    用户请求
     * @param mediaId    通过素材管理中的接口上传多媒体文件，得到的id
     * @param toUserName 要返回的用户openId
     * @return 语音类型的响应
     */
    public static BaseMarsResponse createVoiceResponse(BaseMarsRequest request, String mediaId, String toUserName) {
        MarsVoiceResponse response = new MarsVoiceResponse();
        createResponse(request, response, toUserName);
        response.setMediaId(mediaId);
        return response;
    }

    /**
     * 创建文本类型的响应
     *
     * @param request 用户请求
     * @param content 文本内容
     * @return 文本类型的响应
     */
    public static BaseMarsResponse createTextResponse(BaseMarsRequest request, String content) {
        return createTextResponse(request, content, request.getFromUserName());
    }

    /**
     * 创建文本类型的响应
     *
     * @param request    用户请求
     * @param content    文本内容
     * @param toUserName 要返回的用户openId
     * @return 文本类型的响应
     */
    public static BaseMarsResponse createTextResponse(BaseMarsRequest request, String content, String toUserName) {
        MarsTextResponse response = new MarsTextResponse();
        createResponse(request, response, toUserName);
        response.setContent(content);
        return response;
    }

    /**
     * 创建图片类型的响应
     *
     * @param request 用户请求
     * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id。
     * @return 图片类型的响应
     */
    public static BaseMarsResponse createImageResponse(BaseMarsRequest request, String mediaId) {
        return createImageResponse(request, mediaId, request.getFromUserName());
    }

    /**
     * 创建图片类型的响应
     *
     * @param request    用户请求
     * @param mediaId    通过素材管理中的接口上传多媒体文件，得到的id。
     * @param toUserName 要返回的用户openId
     * @return 图片类型的响应
     */
    public static BaseMarsResponse createImageResponse(BaseMarsRequest request, String mediaId, String toUserName) {
        MarsImageResponse response = new MarsImageResponse();
        createResponse(request, response, toUserName);
        response.setMediaId(mediaId);
        return response;
    }

    private static void createResponse(BaseMarsRequest request, BaseMarsResponse response, String toUserName) {
        response.setFromUserName(request.getToUserName());
        response.setEncryption(request.getEncryption());
        response.setToUserName(toUserName);
    }
}