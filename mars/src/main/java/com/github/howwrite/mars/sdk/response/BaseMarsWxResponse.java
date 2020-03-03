package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.request.MarsWxRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息基本类型
 *
 * @author howwrite
 * @date 2020/3/1 下午2:31:40
 */
public abstract class BaseMarsWxResponse {
    private static final Logger log = LoggerFactory.getLogger(BaseMarsWxResponse.class);
    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方微信号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息类型：{@link WxMsgType}
     */
    private String msgType;

    /**
     * 消息是否加密
     */
    private Boolean encryption;

    public static BaseMarsWxResponse createTextResponse(MarsWxRequest request, String content) {
        return createTextResponse(request, content, request.getFromUserName());
    }

    public static BaseMarsWxResponse createTextResponse(MarsWxRequest request, String content, String toUserName) {
        BaseMarsTextWxResponse response = new BaseMarsTextWxResponse();
        createResponse(request, response, toUserName, WxMsgType.TEXT_TYPE);
        response.setContent(content);
        response.setToUserName(toUserName);
        response.setMsgType(WxMsgType.TEXT_TYPE);
        return response;
    }

    public static BaseMarsWxResponse createImageResponse(MarsWxRequest request, String mediaId) {
        return createImageResponse(request, mediaId, request.getFromUserName());
    }

    public static BaseMarsWxResponse createImageResponse(MarsWxRequest request, String mediaId, String toUserName) {
        BaseMarsImageWxResponse response = new BaseMarsImageWxResponse();
        createResponse(request, response, toUserName, WxMsgType.IMAGE_TYPE);
        response.setMediaId(mediaId);
        return response;
    }

    private static void createResponse(MarsWxRequest request, BaseMarsWxResponse response, String toUserName, String msgType) {
        response.setFromUserName(request.getToUserName());
        response.setEncryption(request.getEncryption());
        response.setToUserName(toUserName);
        response.setMsgType(msgType);
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

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

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
        return String.format(format, toUserName, fromUserName, createTime, msgType);
    }
}
