package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;

/**
 * @author howwrite
 * @date 2020/3/7 下午10:48:12
 */
public class MarsVoiceResponse extends BaseMarsResponse {
    private static final long serialVersionUID = 2117478574593998046L;
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String getMsgType() {
        return WxMsgType.VOICE_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Voice><MediaId><![CDATA[%s]]></MediaId></Voice></xml>";
        return String.format(format, parentXml, mediaId);
    }
}
