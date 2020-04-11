package com.github.howwrite.mars.sdk.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/7 下午10:37:05
 */
@Getter
@Setter
@ToString(callSuper = true)
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
}
