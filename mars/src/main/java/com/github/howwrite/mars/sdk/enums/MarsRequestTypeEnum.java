package com.github.howwrite.mars.sdk.enums;

import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.request.MarsTextRequest;

/**
 * @author howwrite
 * @date 2020/3/4 下午10:32:05
 */
public enum MarsRequestTypeEnum {
    /**
     * 文本消息请求
     */
    TEXT("text", MarsTextRequest.class);

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
            if (anEnum.getSupportType().equals(type)) {
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
