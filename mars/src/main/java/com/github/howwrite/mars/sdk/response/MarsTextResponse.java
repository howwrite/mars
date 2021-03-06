package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.utils.ParamUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/3 上午8:19:02
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsTextResponse extends BaseMarsResponse {
    private static final long serialVersionUID = -3283535043994565082L;
    private String content;

    @Override
    public String getMsgType() {
        return WxMsgType.TEXT_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Content><![CDATA[%s]]></Content></xml>";
        return String.format(format, parentXml, getContent());
    }

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtils.notBlank(getContent(), "The content in the result cannot be empty");
    }
}
