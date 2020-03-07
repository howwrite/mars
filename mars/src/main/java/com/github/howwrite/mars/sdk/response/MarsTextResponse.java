package com.github.howwrite.mars.sdk.response;

/**
 * @author howwrite
 * @date 2020/3/3 上午8:19:02
 */
public class MarsTextResponse extends BaseMarsResponse {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Content><![CDATA[%s]]></Content></xml>";
        return String.format(format, parentXml, content);
    }
}
