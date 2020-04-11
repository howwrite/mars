package com.github.howwrite.mars.sdk.request;

import com.github.howwrite.mars.sdk.annotation.MarsRequestAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author howwrite
 * @date 2020/3/7 下午11:41:41
 */
@Getter
@Setter
@ToString(callSuper = true)
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

    @MarsRequestAnnotation(xmlName = "Location_X")
    public void setLocationX(BigDecimal locationX) {
        this.locationX = locationX;
    }

    @MarsRequestAnnotation(xmlName = "Location_Y")
    public void setLocationY(BigDecimal locationY) {
        this.locationY = locationY;
    }
}
