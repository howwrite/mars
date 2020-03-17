package com.github.howwrite.mars.sdk.request;

/**
 * @author howwrite
 * @date 2020/3/7 下午10:44:22
 */
public class MarsVoiceRequest extends BaseMarsRequest {
    private static final long serialVersionUID = 4462963288033529701L;
    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String format;
    /**
     * 开通语音识别后，识别的文字内容
     */
    private String recognition;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
