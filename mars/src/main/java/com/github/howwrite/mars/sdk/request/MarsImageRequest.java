package com.github.howwrite.mars.sdk.request;

/**
 * @author howwrite
 * @date 2020/3/7 下午10:37:05
 */
public class MarsImageRequest extends BaseMarsRequest {
    private static final long serialVersionUID = -6304669817124099960L;
    /**
     * 图片链接（由系统生成）
     */
    private String picUrl;

    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
