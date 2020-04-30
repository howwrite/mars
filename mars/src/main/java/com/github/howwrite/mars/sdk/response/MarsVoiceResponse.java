package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.utils.ParamUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/7 下午10:48:12
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsVoiceResponse extends BaseMarsResponse {
    private static final long serialVersionUID = 2117478574593998046L;
    private String mediaId;

    @Override
    public String getMsgType() {
        return WxMsgType.VOICE_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Voice><MediaId><![CDATA[%s]]></MediaId></Voice></xml>";
        return String.format(format, parentXml, getMediaId());
    }

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtils.notBlank(getMediaId(), "The mediaId in the result cannot be empty");
    }
}
