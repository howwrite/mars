package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.utils.ParamUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

/**
 * @author howwrite
 * @date 2020/3/7 下午11:08:12
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsVideoResponse extends BaseMarsResponse {
    private static final long serialVersionUID = -218545960930935619L;
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String mediaId;
    /**
     * 视频消息的标题
     */
    private String title;
    /**
     * 视频消息的描述
     */
    private String description;

    @Override
    public String getMsgType() {
        return WxMsgType.VIDEO_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        StringBuilder result = new StringBuilder("<xml>");
        result.append(parentXml);
        result.append("<Video><MediaId><![CDATA[").append(getMediaId()).append("]]></MediaId>");
        if (!StringUtils.isEmpty(getTitle())) {
            result.append("<Title><![CDATA[").append(getTitle()).append("]]></Title>");
        }
        if (!StringUtils.isEmpty(getDescription())) {
            result.append("<Description><![CDATA[").append(getDescription()).append("]]></Description>");
        }
        result.append("</Video></xml>");
        return result.toString();
    }

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtils.notBlank(getMediaId(), "The mediaId in the result cannot be empty");
    }
}
