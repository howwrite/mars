package com.github.howwrite.mars.sdk.enums;

import com.github.howwrite.mars.sdk.request.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @author howwrite
 * @date 2020/3/4 下午10:32:05
 */
public enum MarsRequestTypeEnum {
    /**
     * 文本消息请求
     */
    TEXT("text", MarsTextRequest.class),
    /**
     * 图片类型请求
     */
    IMAGE("image", MarsImageRequest.class),
    /**
     * 语音类型请求
     */
    VOICE("voice", MarsVoiceRequest.class),
    /**
     * 视频类型请求
     */
    VIDEO("video", MarsVideoRequest.class),
    /**
     * 小视频类型请求
     */
    SHORT_VIDEO("shortvideo", MarsShortVideoRequest.class),
    /**
     * 链接类型请求
     */
    LINK("link", MarsLinkRequest.class),
    /**
     * 事件类型请求
     */
    EVENT("event", MarsEventRequest.class),
    /**
     * 上报地理位置事件请求
     */
    LOCATION("location", MarsLocationRequest.class),
    ;

    /**
     * 请求类型
     */
    private String supportType;
    /**
     * 实例化的类
     */
    private Class<? extends BaseMarsRequest> clazz;

    MarsRequestTypeEnum(String supportType, Class<? extends BaseMarsRequest> clazz) {
        this.supportType = supportType;
        this.clazz = clazz;
    }

    /**
     * 通过请求类型实例化请求类类
     *
     * @param type 请求消息类型
     * @return 该类型使用的请求类
     */
    public static Class<? extends BaseMarsRequest> getRequestClazz(String type) {
        MarsRequestTypeEnum[] enums = values();
        for (MarsRequestTypeEnum anEnum : enums) {
            if (StringUtils.equalsIgnoreCase(anEnum.getSupportType(), type)) {
                return anEnum.getClazz();
            }
        }
        return null;
    }

    public String getSupportType() {
        return supportType;
    }

    public Class<? extends BaseMarsRequest> getClazz() {
        return clazz;
    }
}
