package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;

/**
 * @author howwrite
 * @date 2020/3/7 下午11:08:12
 */
public class MarsVideoResponse extends BaseMarsResponse {
    private static final long serialVersionUID = -218545960930935619L;
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String mediaId;
    /**
     * 视频消息的标题
     */
    private String title;
    /**
     * 视频消息的描述
     */
    private String description;

    @Override
    public String getMsgType() {
        return WxMsgType.VIDEO_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Video><MediaId><![CDATA[%s]]></MediaId><Title><![CDATA[%s]]></Title><Description><![CDATA[%s]]></Description></Video></xml>";
        return String.format(format, parentXml, mediaId, title, description);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
