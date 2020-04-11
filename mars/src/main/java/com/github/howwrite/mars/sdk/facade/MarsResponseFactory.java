package com.github.howwrite.mars.sdk.facade;

import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.response.*;
import com.github.howwrite.mars.sdk.response.param.NewsResponseParam;

import java.util.List;

/**
 * @author howwrite
 * @date 2020/3/28 上午1:21:06
 */
public class MarsResponseFactory {

    public static BaseMarsResponse createNewsResponse(BaseMarsRequest request, List<NewsResponseParam> params) {
        return createNewsResponse(request, params, request.getFromUserName());
    }

    public static BaseMarsResponse createNewsResponse(BaseMarsRequest request, List<NewsResponseParam> articles, String toUserName) {
        MarsNewsResponse response = new MarsNewsResponse();
        createResponse(request, response, toUserName);
        response.setArticles(articles);
        return response;
    }

    public static BaseMarsResponse createMusicResponse(BaseMarsRequest request, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) {
        return createMusicResponse(request, title, description, musicUrl, hqMusicUrl, thumbMediaId, request.getFromUserName());
    }

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

    public static BaseMarsResponse createVideoResponse(BaseMarsRequest request, String mediaId, String title, String description) {
        return createVideoResponse(request, mediaId, title, description, request.getFromUserName());
    }

    public static BaseMarsResponse createVideoResponse(BaseMarsRequest request, String mediaId, String title, String description, String toUserName) {
        MarsVideoResponse response = new MarsVideoResponse();
        createResponse(request, response, toUserName);
        response.setMediaId(mediaId);
        response.setTitle(title);
        response.setDescription(description);
        return response;
    }

    public static BaseMarsResponse createVoiceResponse(BaseMarsRequest request, String mediaId) {
        return createVoiceResponse(request, mediaId, request.getFromUserName());
    }

    public static BaseMarsResponse createVoiceResponse(BaseMarsRequest request, String mediaId, String toUserName) {
        MarsVoiceResponse response = new MarsVoiceResponse();
        createResponse(request, response, toUserName);
        response.setMediaId(mediaId);
        return response;
    }

    public static BaseMarsResponse createTextResponse(BaseMarsRequest request, String content) {
        return createTextResponse(request, content, request.getFromUserName());
    }

    public static BaseMarsResponse createTextResponse(BaseMarsRequest request, String content, String toUserName) {
        MarsTextResponse response = new MarsTextResponse();
        createResponse(request, response, toUserName);
        response.setContent(content);
        return response;
    }

    public static BaseMarsResponse createImageResponse(BaseMarsRequest request, String mediaId) {
        return createImageResponse(request, mediaId, request.getFromUserName());
    }

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
