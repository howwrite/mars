package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.request.WxRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @author howwrite
 * @date 2020/3/1 下午2:31:40
 */
public class WxResponse {
    private static final Logger log = LoggerFactory.getLogger(WxResponse.class);
    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方微信号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息创建时间
     */
    private Long createTime;

    /**
     * 消息类型：文本为text
     */
    private String msgType;

    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 消息是否加密
     */
    private Boolean encryption;

    public static WxResponse createTextResponse(WxRequest request, String content) {
        return createTextResponse(request, content, request.getFromUserName());
    }

    public static WxResponse createTextResponse(WxRequest request, String content, String toUserName) {
        WxResponse result = createResponse(request);
        result.setContent(content);
        result.setToUserName(toUserName);
        result.setMsgType(WxMsgType.TEXT_TYPE);
        return result;
    }

    private static WxResponse createResponse(WxRequest request) {
        WxResponse response = new WxResponse();
        response.setCreateTime(request.getCreateTime());
        response.setFromUserName(request.getToUserName());
        response.setEncryption(request.getEncryption());
        return response;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 将此对象所有非空
     *
     * @return
     */
    public String convertXmlString(String createTime) {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder("<xml>");
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            if (field.getType().equals(String.class)) {
                try {
                    String value = (String) field.get(this);
                    String name = StringUtils.capitalize(field.getName());
                    if (!StringUtils.isEmpty(value)) {
                        sb.append(String.format("<%s>%s</%s>", name, value, name));
                    }
                } catch (IllegalAccessException e) {
                    log.warn("Reflection failed", e);
                }
            }
        }
        sb.append("<CreateTime>");
        sb.append(createTime);
        sb.append("</CreateTime></xml>");
        return sb.toString();
    }
}
