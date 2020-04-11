package com.github.howwrite.mars.sdk.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/7 下午10:44:22
 */
@Getter
@Setter
@ToString(callSuper = true)
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
}
