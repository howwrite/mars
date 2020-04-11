package com.github.howwrite.mars.sdk.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/7 下午11:34:58
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsShortVideoRequest extends BaseMarsRequest {
    private static final long serialVersionUID = 6036481900365666475L;
    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String thumbMediaId;
}
