package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.response.param.NewsResponseParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * 消息基本类型
 *
 * @author howwrite
 * @date 2020/3/1 下午2:31:40
 */
public abstract class BaseMarsResponse implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(BaseMarsResponse.class);
    private static final long serialVersionUID = 7502062347511247255L;
    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方微信号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息是否加密
     */
    private Boolean encryption;

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

    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    /**
     * {@link WxMsgType}
     *
     * @return 回复的消息类型
     */
    public abstract String getMsgType();

    /**
     * 将此对象所有字段值类型为String且非空的值拼装成xml
     *
     * @param createTime 此xml的创建时间，会拼装在xml的<Create></Create>中
     * @return xml字符串
     */
    public String convertXmlString(String createTime) {
        String format = "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "  <CreateTime>%s</CreateTime>\n" +
                "  <MsgType><![CDATA[%s]]></MsgType>";
        return String.format(format, toUserName, fromUserName, createTime, getMsgType());
    }
}
