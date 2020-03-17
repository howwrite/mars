package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;

/**
 * @author howwrite
 * @date 2020/3/8 上午8:43:51
 */
public class MarsMusicResponse extends BaseMarsResponse {
    private static final long serialVersionUID = -7434226028895186911L;
    /**
     * 音乐标题
     */
    private String title;
    /**
     * 音乐描述
     */
    private String description;
    /**
     * 音乐链接
     */
    private String musicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String hqMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String thumbMediaId;

    @Override
    public String getMsgType() {
        return WxMsgType.MUSIC_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Video><MediaId><![CDATA[%s]]></MediaId><Title><![CDATA[%s]]></Title><Description><![CDATA[%s]]></Description></Video></xml>";
        return String.format(format, parentXml, musicUrl, title, description);
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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
