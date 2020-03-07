package com.github.howwrite.mars.sdk.response;

/**
 * @author howwrite
 * @date 2020/3/3 上午8:20:29
 */
public class MarsImageResponse extends BaseMarsResponse {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Image><MediaId><![CDATA[%s]]></MediaId></Image></xml>";
        return String.format(format, parentXml, mediaId);
    }
}
