package com.github.howwrite.mars.sdk.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author howwrite
 * @date 2020/3/7 下午11:06:07
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsVideoRequest extends BaseMarsRequest {
    private static final long serialVersionUID = 7724453961114581676L;
    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;
}
