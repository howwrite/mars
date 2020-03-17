package com.github.howwrite.mars.sdk.request;

/**
 * 相关文档 https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_event_pushes.html
 *
 * @author howwrite
 * @version 1.0
 * @date 2020/3/17 上午8:27:20
 */
public class MarsEventRequest extends BaseMarsRequest {
    private static final long serialVersionUID = -6161588347248337756L;
    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、location(上报地理位置事件)、view(点击菜单跳转链接时的事件推送)
     */
    private String event;

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    /**
     * 地理位置纬度
     */
    private String latitude;

    /**
     * 地理位置经度
     */
    private String longitude;

    /**
     * 地理位置精度
     */
    private String precision;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}
