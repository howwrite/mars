package com.github.howwrite.mars.sdk.request;

import com.github.howwrite.mars.sdk.annotation.MarsRequestAnnotation;

import java.math.BigDecimal;

/**
 * @author howwrite
 * @date 2020/3/7 下午11:41:41
 */
public class MarsLocationRequest extends BaseMarsRequest {
    private static final long serialVersionUID = 2924402994234787985L;
    /**
     * 地理位置纬度
     */
    private BigDecimal locationX;
    /**
     * 地理位置经度
     */
    private BigDecimal locationY;
    /**
     * 地图缩放大小
     */
    private Integer scale;
    /**
     * 地理位置信息
     */
    private String label;

    public BigDecimal getLocationX() {
        return locationX;
    }

    @MarsRequestAnnotation(xmlName = "Location_X")
    public void setLocationX(BigDecimal locationX) {
        this.locationX = locationX;
    }

    public BigDecimal getLocationY() {
        return locationY;
    }

    @MarsRequestAnnotation(xmlName = "Location_Y")
    public void setLocationY(BigDecimal locationY) {
        this.locationY = locationY;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
