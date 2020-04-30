package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.utils.ParamUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 消息基本类型
 *
 * @author howwrite
 * @date 2020/3/1 下午2:31:40
 */
@Getter
@Setter
@ToString(callSuper = true)
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
    private Boolean encryption = false;

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
        return String.format(format, getToUserName(), getFromUserName(), createTime, getMsgType());
    }

    public void checkParam() {
        ParamUtils.notBlank(getToUserName(), "ToUserName in response cannot be empty");
        ParamUtils.notBlank(getFromUserName(), "FromUserName in response cannot be empty");
    }
}
